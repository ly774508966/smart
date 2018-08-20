### 框架说明
smart源于奔驰smart，表明了此框架的核心追求是“小而精”，基于实际使用场景，平衡了质量、效率、成本，产生了此框架。

框架使用了目前主流的前后台分离的开发方案，网站前端使用Vue2.0，后端使用SpringBoot2.0，后端接口同时提供服务给网站前端、APP使用。

### 接口项目结构
``` lua
    smart-api
    ├── api -- 接口层代码 
    ├    ├── assembly -- 构建配置
    ├    ├── java -- 配置
    ├    ├    ├── aop -- 切面  
    ├    ├    ├── config -- 配置  
    ├    ├    ├── controller -- 接口
    ├    ├    ├── filter -- 拦截器
    ├    ├    ├── shiro -- 权限管理
    ├    ├    ├── task -- 任务
    ├    ├    ├── util -- 网站工具
    ├    ├    ├── Application.java -- 启动入口
    ├    ├── resource -- 资源
    ├    ├    ├── application.properties -- 配置文件
    ├    ├    ├── logback.xml -- 日志配置
    ├── service -- 服务层
    ├── dao -- 数据库访问层
    ├── util -- 工具类层
```