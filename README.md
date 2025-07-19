# 十一实验新生入校活动系统

***

**注：本系统为半成品，仅完成核心打卡功能，后续使用仍需继续开发**

### 一、系统环境

本系统基于 ***Java18*** 开发，数据库使用***MySQL 9.3.0社区版*** 后续开发请注意环境问题

### 二、使用方法

#### 1. 不论你打算将其运行在*Windows*/*Mac*/*Liunx*上，请务必先安装***Java18***和***MySQL***

MySQL可以在从MySQL官网下载(https://dev.mysql.com/downloads/mysql/)  
安装MySQL时，请***牢记你的用户名与密码***

Java可以从Java的官网下载(https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

安装Java后，跟随网上博客配置Java的环境变量，个人亲测，jre可不用配置


#### 2. 修改配置文件

##### 若不会使用Maven进行编译，请选择下面的方式  
使用电脑上的压缩包工具如：将您下载到的jar包，名字应该形如  
`SignIn-0.0.1-SNAPSHOT.jar`的文件后缀改为`.zip`并解压缩  
找到`BOOT-INF/classes/application.properties`并打开  
修改
``` 
spring.datasource.username=root
spring.datasource.password=rootroot
```
中的*username*字段和*password*字段，将其替换为安装MySQL数据时所设置的用户名和密码  
用压缩工具重新压缩整个*SignIn-0.0.1-SNAPSHOT*文件夹，压缩后将.zip的后缀改回.jar

#### 3. 创建并导入数据库结构
打开电脑的终端（cmd）执行  
```
cd C:\Program Files\MySQL\MySQL Server 9.3\bin
mysql -u username -p
```
其中`username`改为自己设置的用户名，输入密码后命令提示符应该变为  
`mysql>`  
然后依次输入`CREATE DATABASE information;`和`USE information`  
最后执行 `SOURCE path/information.sql;`  
(path为您电脑里information.sql的路径)  
此时您的命令提示符窗口将会输出很多`Query OK`字样的内容，这是正常现象

#### 4. 运行程序
恭喜你已经完成了前面必要的环境配置，马上就能运行了！  
回到刚才重新压缩好的jar包的文件夹，右键，选择*在终端中打开*  
在终端中输入`java -jar SignIn-0.0.1-SNAPSHOT.jar`  
如果走到这里，一切正常，恭喜你完成了这个项目的运行  
在浏览器中输入`http:localhost:8080/hello`你将成功的打开这个项目