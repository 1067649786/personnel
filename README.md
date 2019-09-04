
## 项目整体功能结构

![图片描述](https://doc.shiyanlou.com/courses/uid987099-20190806-1565083158238/wm)

项目总共分为五大功能模块，分别是员工资料、人事管理、薪酬管理、统计管理和系统管理

## 快速启动

在实验环境中，创建好数据库后，切换工作空间进入`vhr`目录通过`mvn spring-boot:run`就可以运行了。

如果不在实验楼的在线环境运行，要在自己本地环境运行就需要以下步骤：

1.下载项目到本地

2.在 MySQL 中执行数据库脚本

3.将 application.properties 文件中的`server.port`改成 8082，因为前端启动的时候会占用 8080 端口

4.在 IntelliJ IDEA 中运行 vhr 项目

**OK，至此，服务端就启动成功了，此时我们直接在地址栏输入http://localhost:8082/index.html 即可访问我们的项目，如果要做二次开发，请继续看第五、六步。**

5.进入到 vuehr 目录中，在命令行依次输入如下命令：

```bash
# 安装依赖
npm install

# 在 localhost:8080 启动项目
npm run dev
```

由于我在 vuehr 项目中已经配置了端口转发，将数据转发到 SpringBoot 上，因此项目启动之后，在浏览器中输入 http://localhost:8080 就可以访问我们的前端项目了，所有的请求通过端口转发将数据传到 SpringBoot 中（注意此时不要关闭 SpringBoot 项目）。

6.最后可以用 IntelliJ IDEA 等工具打开 vuehr 项目，继续开发，开发完成后，当项目要上线时，依然进入到 vuehr 目录，然后执行如下命令：

```bash
npm run build
```

该命令执行成功之后，vuehr 目录下生成一个 dist 文件夹，将该文件夹中的两个文件 static 和 index.html 拷贝到 SpringBoot 项目中 resources/static/目录下，然后就可以像第 4 步那样直接访问了。

