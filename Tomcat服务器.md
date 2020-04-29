# Tomcat服务器

java 代码的一个很重要的作用就是进行数据的处理，但是运行完毕后，如果需要再次运行则需要再次手动启动代码的执行。但是我们无法提前用户会何时发送请求，也就无法决定我们编写的 java 代码应该什么时候启动运行。而且手动运行也变得不现实



**服务器：一个可以根据用户的请求来启动并运行我们编写的数据逻辑代码的容器**



\bin 存放启动和关闭 Tomcat 的可执行文件

\conf 存放Tomcat 的配置文件

\lib 存放库文件

\logs 存放日志文件

\temp 存放临时文件

\webapps 存放web 应用，默认在这里读项目。

\work 存放JSP 转换后的Servlet 文件



- 启动服务器：startup.bat
- 服务器不要关闭，关闭就无法连接
- 默认端口号8080
- 输入网址 localhost:8080



![image-20200426010343789](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426010343789.png)

