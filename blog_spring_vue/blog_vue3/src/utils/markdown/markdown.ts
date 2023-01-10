// @ts-ignore
import {marked} from "marked"

// 生成目录
const tocObj = {
    add: function (text: string, level: number) {
        const anchor = `toc${level}${++this.index}`;
        // @ts-ignore
        this.toc.push({
            anchor: anchor,
            level: level,
            text: text
        });
        return anchor;
    },
    // 使用堆栈的方式处理嵌套的ul,li，level即ul的嵌套层次，1是最外层
    // <ul>
    //   <li></li>
    //   <ul>
    //     <li></li>     'id', 'name',
    //   </ul>
    //   <li></li>
    // </ul>
    toHTML: function () {
        let levelStack: [] = [];
        let result = "";
        const addStartUL = () => {
            result += '<ul class="anchor-ul">';  //  id="anchor-fix"
        };
        const addEndUL = () => {
            result += "</ul>\n";
        };
        const addLI = (anchor: string, text: string) => {
            result +=
                '<li><a class="toc-link" href="#' + anchor + '">' + text + "</a></li>\n";
        };
        // console.log(this.toc)
        this.toc.forEach(function (item) {
            // @ts-ignore
            let levelIndex = levelStack.indexOf(item.level);
            // 没有找到相应level的ul标签，则将li放入新增的ul中
            if (levelIndex === -1) {
                // @ts-ignore
                levelStack.unshift(item.level);
                addStartUL();
                // @ts-ignore
                addLI(item.anchor, item.text);
            } // 找到了相应level的ul标签，并且在栈顶的位置则直接将li放在此ul下
            else if (levelIndex === 0) {
                // @ts-ignore
                addLI(item.anchor, item.text);
            } // 找到了相应level的ul标签，但是不在栈顶位置，需要将之前的所有level出栈并且打上闭合标签，最后新增li
            else {
                while (levelIndex--) {
                    levelStack.shift();
                    addEndUL();
                }
                // @ts-ignore
                addLI(item.anchor, item.text);
            }
        });
        // 如果栈中还有level，全部出栈打上闭合标签
        while (levelStack.length) {
            levelStack.shift();
            addEndUL();
        }
        // 清理先前数据供下次使用
        this.toc = [];
        this.index = 0;
        return result;
    },

    toTocList: function () {
        let res = ""
        res += '<ul class="anchor-ul" id="anchor-fix">';
        this.toc.forEach(item => {
            // @ts-ignore
            res += `<li><a href="#${item.anchor}">${item.text}</a></li>\n`;
        })
        res += '</ul>';

        this.toc = [];
        this.index = 0;
        // console.log(res)
        return res
    },
    toc: [],
    index: 0
};

// let matchMapList = marked.parse(this.mdContent).match(/(<h)[\d\s]+(id=)["'\w-]+>[^<]+</g).map(item => {
//     let curretItem = item.match(/(<h)[\d\s]+(id=)["'\w-]+>/g)[0]
//     return {
//         hName: Number(curretItem.match(/<h[\d]+/)[0].slice(2)),
//         id: curretItem.match(/id=[\S]+/)[0].slice(4,-2),
//         liName: item.slice(curretItem.length, -1).trim(),
//         children: []
//     }
// })

class MarkUtils {
    private readonly rendererMD: marked.Renderer;
    // private maxTitle: 0;

    constructor() {
        this.rendererMD = new marked.Renderer();
        this.rendererMD.heading = function (text: string, level: number) {
            const anchor = tocObj.add(text, level);
            return `<h${level} id="${anchor}">${text}</h${level}>\n`;
        };
        // 代码块部分在指令中渲染 效率更高
        // this.rendererMD.code = function (code: string, lang: string, escaped: string) {
        //
        //     const div = `<div @click="copy-btn" class="code-button">复制</div>`
        //
        //     // code 根据 \n 分割
        //     let lines = code.split('\n')
        //     let result = ''
        //     lines.forEach(line => {
        //         result += `<li>${line}</li>`
        //     })
        //
        //     console.log(code.slice(0, 10), lang)
        //
        //     // 如果没有 language，就直接返回中文
        //     if (!lang) {
        //         return '<pre><code><ol>'
        //             + result
        //             + '\n</ol></code></pre>';
        //     }
        //
        //     return '<pre><code>'
        //         + result
        //         + '\n</code></pre>\n';
        // }
        this.rendererMD.image = function (href: string, title: string, text: string) {
            return `<img 
                @click="showImage(event, '${href}')" 
                data-src="${href}" 
                alt="照片丢失了，请联系管理员" 
                title="${title ? title : ""}"
                style="max-width: 100%"/>`
        }

        marked.setOptions({
            renderer: this.rendererMD,
            headerIds: false,
            gfm: true,
            tables: true,
            breaks: false,
            pedantic: false,
            sanitize: false,
            smartLists: true,
            smartypants: false,
        });
    }

    async marked(data: string) {
        if (data) {
            let content = await marked(data); // 文章内容
            // let toc = tocObj.toHTML(); // 文章目录 递归
            let toc = tocObj.toTocList();   // 直接
            return {
                content,
                toc
            };
        } else {
            return null;
        }
    }
}

const markdown = new MarkUtils();

export default markdown;