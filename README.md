#小说社区
[Spring]

#知识点
[session与cookie]
#脚本
[sql]
create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    BIO          VARCHAR(256),
    constraint USER_PK
        primary key (ID)
);

#使用Gihub托管代码
#明确需求
#使用Bookstrap编写导航栏样式
#注册GithubApp
#完成Github登陆流程
1、Github登陆之调用authorize
2、获取code
3、获取用户信息
#配置application.properties
#Session与Cookies的实现
#使用h2数据库、集成mybatis
#实现持久化登陆状态获取Cookie，拿到token。写入session
#集成Flyway Migration ：数据库迁移,Flyway帮助去执行sql脚本。
...
   独立于数据库的应用、管理并跟踪数据库变更的数据库版本管理工具。
   Flyway可以像Git管理不同人的代码那样,管理不同人的sql脚本.
...
#用bookstrap编写发布问题页面
#完成发布文章的功能，post方式提交表单，写入的信息到服务端。
#添加Lombok支持,idea默认没有安装lombok插件
#完成首页问题列表：
    头像、描述等
#解决text area使用th:value不能回显问题，应用th:text
[问题]错误提示没有的时候，发布按钮飘到了左边。列表页日期问题
#实现分页功能
-- auto-generated definition
create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    BIO          VARCHAR(256),
    AVATAR_URL   VARCHAR(100),
    constraint USER_PK
        primary key (ID)
);

-- auto-generated definition
create table QUESTION
(
    ID            INT auto_increment,
    TITLE         VARCHAR(50),
    DESCRIPTION   TEXT,
    GMT_CREATE    BIGINT,
    GMT_MODIFIED  BIGINT,
    CREATOR       INT,
    COMMENT_COUNT INT default 0,
    VIEW_COUNT    INT default 0,
    LIKE_COUNT    INT default 0,
    COLUMN_10     INT,
    TAG           VARCHAR(256),
    constraint QUESTION_PK
        primary key (ID)
);
#完善导航栏并进行页面拆解。
[语法]：<div th:insert="~{navigation::nav}"></div>
#个人资料发布问题列表实现。