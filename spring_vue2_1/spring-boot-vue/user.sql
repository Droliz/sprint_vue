use test;

# 创建表
create table user (
  id int auto_increment comment '主键' primary key,
  no varchar(20) null comment '账号',
  name varchar(100) not null comment '名字',
  password varchar(20) not null comment '密码',
  age int null,
  sex int null comment '性别',
  phone varchar(20) null comment '电话',
  role_id int null comment '角色：0超级管理员、1管理员、2普通账号',
  isValid varchar(4) null comment '是否有效'
)
    charset =utf8
    AUTO_INCREMENT=1;

# 插入初始数据
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (1, 'sa', '超级管理员', '123', 18, 1, '111', 0, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (2, 'ad', '管理员', '123', 12, 1, '222', 1, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (3, 'admin', '小米', '123', 18, 1, '1212121', 1, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (4, 'wqeqweq', '3q3werwqer', '12123123', 23, 0, '123213', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (5, 'adwa', '小明', '123123', 21, 1, '12312321', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (6, 'wdada', '小红', '213123', 21, 0, '123123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (7, '213123', '小五', '21312', 23, 1, '21312312', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (8, '123123', '小曼', '12312', 21, 0, '2323', 1, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (9, '2312', '阿红', '1231231', 18, 0, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (10, '2312', '阿红', '1231231', 18, 0, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (11, '2312', '阿红', '1231231', 18, 0, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (12, '2312', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (13, '12sada', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (14, '12sada', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (15, '12sada', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (16, '12sada', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (17, '12sada', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (18, '12sada', '阿七', '1231231', 18, 1, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (19, '12sada', '阿红', '1231231', 18, 0, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (20, '12sada', '阿红', '1231231', 18, 0, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (21, '12sada', '阿红', '1231231', 18, 0, '123123', 2, 'Y');
INSERT INTO test.user (id, no, name, password, age, sex, phone, role_id, isValid) VALUES (22, '12sada', '阿红', '1231231', 123, 0, '123123', 2, 'Y');

# 重置数据主键
set @rownum = 0;
UPDATE test.user set id = @rownum := @rownum+1;

# 重置起始自增值
ALTER TABLE table_name AUTO_INCREMENT = 24;