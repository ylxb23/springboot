# springboot
springboot学习项目

## 入口类
`org.zero.boot.web.init.App`

## 数据库连接
连接信息在 datasource.properties 文件中，对应的数据库表结构内容在 `sql`文件夹中的脚本。用户名密码信息自行替换。
使用三种连接方式：
1、第一种：`org.zero.boot.web.init.conf.FirstDataSourceConfiguration` 配置内容，对应的数据库`zero`

2、第二种：`org.zero.boot.web.init.conf.SecondDataSourceConfiguration` 配置内容，对应数据库`sama`

3、第三种：`org.zero.boot.web.init.conf.ThirdDataSourceConfig` 配置内容，对应数据库`third`

## Redis连接
连接信息在 datasource.properties 文件中。

## 邮件发送
对应配置信息在 plugins.properties 文件中，对应的配置项`spring.mail.password`为对应邮箱的授权码，可以使用腾讯QQ的邮箱账户，在“设置”-“POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务”中开启“生成授权码”服务，对应的`spring.mail.username`为QQ邮箱账号，`spring.mail.password`为获取的授权码。

## 日志输出
使用log4j2日志输出（异步日志），日志打印信息配置详见 log4j2.yml 配置文件。
