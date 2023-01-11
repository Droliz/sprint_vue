import * as fs from "fs";
import * as path from "path"; // 导入fs库和path库，哪里来的？npm, yarn了解一下

const mysql = require("mysql");

const MYSQL_CONF = {
    host: "localhost",
    user: "root",
    password: "123456",
    port: "3306",
    database: "blog",
    // connectionLimit: 20
};
const conn = mysql.createConnection(MYSQL_CONF);
conn.connect(function (err: Error) {
    if (err) throw err;
    console.log("Connected to DB");
});

// 读取文件的逻辑拉出
let temp: string[] = [];

let dirs = [".obsidian", ".git", "markdown_img", ".obsidian"];

const readFilesLists = (dir: string, filesList = []) => {
    const files = fs.readdirSync(dir);

    // console.log(files);
    files.forEach((item) => {
        const temp = item.split(".");

        if (dirs.includes(item)) {
            return;
        }
        var fullPath = path.join(dir, item);
        const stat = fs.statSync(fullPath, { bigint: true });

        if (stat.isDirectory()) {
            readFilesLists(path.join(dir, item), filesList); //递归读取文件
        } else if (temp[temp.length - 1] === "md") {
            const text = fs.readFileSync(fullPath);
            // @ts-ignore
            filesList.push({ name: temp[0], stat: stat, text: text });
        }
    });
    return filesList;
};

// @ts-ignore
const filesList = readFilesLists("F:\\Note", temp);

// const a = new Date(fliesList[0].stat.birthtime)

// @ts-ignore
console.log(filesList[1].stat.birthtime);

let values: [] = [];

filesList.forEach((item) => {
    // @ts-ignore
    let temp = item.stat.birthtime;
    // console.log(item);
    const create_time = `${temp.getFullYear()}-${
        temp.getMonth() + 1
    }-${temp.getDate()} ${temp.getHours()}:${temp.getMinutes()}:${temp.getSeconds()}`;
    // @ts-ignore
    temp = item.stat.mtime;
    const update_time = `${temp.getFullYear()}-${
        temp.getMonth() + 1
    }-${temp.getDate()} ${temp.getHours()}:${temp.getMinutes()}:${temp.getSeconds()}`;

    // @ts-ignore
    values.push([item.stat.ino, item.name, item.text.toString(), create_time, update_time])

});

// console.log(values[123][2])

const sql =
    "INSERT INTO blogs (`blog_id`, `title`, `content`, `create_time`, `update_time`) VALUES ?";
conn.query(sql, [values], function (err: Error, result: []) {
    if (err) {
        console.log()
        throw err

    };
    console.log(result);
});
