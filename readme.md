# 基础能力
``` lua
### 日志能力
    控制台、文本日志
    按天记日志
    独立日志
### 缓存能力
    定时过期
### 定时任务能力
### 数据库层能力
    代码自动生成
### AOP能力
    请求拦截 
    方法拦截 
### 配置文件使用
    自定义配置
### 常用工具类
    数组操作
    字符串操作
    随机数生成 
    日期函数
    Http、Https请求
    加解密（AES、MD5、Base64、RSA）
    File读写
    Xml序列化、反序列化
    Json序列化、反序列化
    Excel操作
    图片操作（放大、缩小、旋转、水印、压缩、格式转换）
    消息发布、订阅
```

# 框架能力
``` lua
### 接口协议
    接口协议定义
    前台统一管理   //TODO 开发中
    前台mock  //TODO 开发中
### 用户权限管理
    需登录  //TODO 开发中
    不可越权  //TODO 开发中
### 异常处理
    异常堆栈记录
    接口、页面异常返回
    404、500页面
### 参数校验
    非空
    手机号
    字符串长度
    数字大小
    IP
    身份证
    分页大小
### 日志管理
    页面、接口请求日志记录
    数据库慢查询日志
    用户登录时间、IP记录 //TODO 开发中
### 项目管理
    本地jar包引入
    项目打包发布
    热更新
### 页面缓存
### 页面模板
    复用 //TODO 开发中
    for if //TODO 开发中
    传值  //TODO 开发中
```

# 业务能力
``` lua
### 基础功能 //TODO 开发中
    登录
    注销
### 公告管理 //TODO 开发中
### 用户管理 //TODO 开发中
    状态管理
    角色管理
    部门管理
### 角色管理 //TODO 开发中
    权限管理
### 数据字典管理 //TODO 开发中
    部门管理
### 日志管理 //TODO 开发中
    用户行为日志记录
    用户行为日志检索
```
    
# 实现
### 项目结构
``` lua
    web-api
    ├── assembly -- 打包脚本
    ├── java -- 代码
    ├    ├── config -- 配置
    ├    ├── controller -- 控制层
    ├    ├── core -- 框架代码
    ├    ├── dto -- 传输对象
    ├    ├── filter -- 拦截器
    ├    ├── model -- 领域模型
    ├    ├── Application.java -- 启动入口
    ├── lib -- 本地jar包
    ├── resource -- 资源
    ├    ├── public -- 静态资源
    ├    ├── template -- 模板资源
    ├    ├── application.properties -- 配置文件
    ├    ├── logback.xml -- 日志配置
```

### 技术选型
    框架：springboot2.0
    日志：logback
    缓存：redis
    模板：thymeleaf
    鉴权：shiro
    数据库：mybatis、mybatis-generator、pagehelper