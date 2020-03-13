#springboot小说社区:使用爬虫爬取某网站小说+小说问答+ github登陆功能。时间紧迫小说阅读界面未完善
[Spring]
时间：1月份至今
#知识点  
[session与cookie]  
#脚本  
[sql]  

#1、使用Gihub托管代码  
#2、明确需求  
#3、使用Bookstrap编写导航栏样式  
#4、注册GithubApp  
#5、完成Github登陆流程  
1、Github登陆之调用authorize   
2、获取code   
3、获取用户信息   
#6、配置application.properties
#7、Session与Cookies的实现
#8、使用h2数据库、集成mybatis
#9、实现持久化登陆状态获取Cookie，拿到token。写入session
#10、集成Flyway Migration ：数据库迁移,Flyway帮助去执行sql脚本。
...
  4 独立于数据库的应用、管理并跟踪数据库变更的数据库版本管理工具。
   Flyway可以像Git管理不同人的代码那样,管理不同人的sql脚本.
...
#11、用bookstrap编写发布问题页面
#12、完成发布文章的功能，post方式提交表单，写入的信息到服务端。
#13、添加Lombok支持,idea默认没有安装lombok插件
#14、完成首页问题列表：
    头像、描述等
#解决text area使用th:value不能回显问题，应用th:text
[问题]错误提示没有的时候，发布按钮飘到了左边。列表页日期问题
#15、实现分页功能
#16、完善导航栏并进行页面拆解。
#17、个人资料发布问题列表实现。
#18、实现通知功能、一级评论、二级评论
#19、支持markdown
#20、使用PageHelper实现分页功能
#21、实现jsoup HTTP client小说爬虫功能及其阅读
#界面还没优化
主界面
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/0e9f4f1f2a7cd13863e10a337094cdc.png)
问答区
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/1584104372(1).jpg)
发布
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/4328f8a870c5ff93c96df4d6f1b0208.png)
通知
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/70fce34e9cb78a3ec275995adc4e0ff.png)
我的问题
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/9f3bfdc470a85098ee7058ecf132c0d.png)
问题详情
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/b2d86b035613b18a41bb29e9830471c.png)
小说界面（界面待优化）
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/baec12de134743f19f34276ce84e5ac.png)
小说信息（待优化）
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/baec12de134743f19f34276ce84e5ac.png)
小说阅读（待优化）
![Image text](https://github.com/oopnull/mycommunity/blob/master/NovelCommunity-imges/c36695fda0c0bfc75444be83b9c8358.png)