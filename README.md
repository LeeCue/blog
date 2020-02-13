### 个人博客
&emsp;&emsp;个人博客系统1.0，已正式上线。[访问网站](http://leecue.xyz)
#### 总述
&emsp;&emsp;从开始学习到现在已经过去半年，这是自己第一次完整做出来的项目，
内心还是十分激动的，虽然用的技术并不是十分多，
作为个人博客系统已经足够了，后期的优化放到2.0版本...
#### 技术栈
- 前端: HTML + CSS + jQuery
- 前端UI: Semantic UI 前端UI框架
- 后端: SpringBoot + Spring MVC + MyBatis + thymeleaf模板
- 数据库连接池: Druid
- 数据库: MySQL 8.0  
#### 工具与环境
- IDEA
- Maven
- JDK 8
#### 插件
- [编辑器 Markdown](https://pandao.github.io/editor.md/)
- [内容排版 typo.css](https://github.com/sofish/typo.css)
- [动画 animate.css](https://daneden.github.io/animate.css/)
- [代码高亮 prism](https://github.com/PrismJS/prism)
- [目录生成 Tocbot](https://tscanlin.github.io/tocbot/)
- [滚动侦测 waypoints](http://imakewebthings.com/waypoints/)
- [平滑滚动 jquery.scrollTo](https://github.com/flesler/jquery.scrollTo)
- [二维码生成 qrcode.js](https://davidshimjs.github.io/qrcodejs/)  
#### 收获
&emsp;&emsp;在本次项目中，收获十分多，调试程序、使用日志来监控、多级评论的设计，表之间连接。  
&emsp;&emsp;DAO层，数据库关系的建立，表之间的外键如果约束，
数据的校验，使用MyBatis进行级联查询，以及级联查询存在的一些问题。一级缓存和二级缓存失效。  
&emsp;&emsp;web层，从前端如何取数据，以及对前端数据的校验，目前没有做非常多的判断逻辑，仅仅是判断非空，
后期会使用JSR 330来校验数据。  
&emsp;&emsp;service层，业务逻辑的处理，如多级评论如何取出，子评论和父评论建立关系。
处理热点数据，对热点数据进行排序，目前做的比较简单，使用comparator比较器来进行。
#### 存在的问题
&emsp;&emsp;数据的缓存，由于MyBatis的二级缓存是在统一命名空间下使用SqlSessionFactory缓存，
我使用的级联查询是多条SQL语句来动态查询，也就是需要调用不同的命名空间下的sql，如果统一命名空间下触发了flushcache
那么缓存将会失效。所以缓存效果并不理想。后期会使用Redis来缓存，比如热点文章，以及最新推荐。  
&emsp;&emsp;权限控制，在本次设计中，仅仅使用web的拦截器来拦截请求，并判断session中的user信息。
这样十分不安全。如果别人获取到session信息，那么可以模拟管理员登录。还有并发控制访问，没有设计。
也就是同一个管理员可以异地登录，还有一些敏感操作没有得到限制。例如，删除一篇博客，修改个人信息。
这些问题，后期会使用shiro来解决。
- - -
### 感谢
&emsp;&emsp;由于是第一次做项目，前端的渲染，以及模板的设计，都不是很熟练，甚至
一点都不懂。借鉴B站上 李仁密 的[教学视频](https://www.bilibili.com/video/av62555970)
做出页面以及如何使用thymeleaf模板来渲染。

