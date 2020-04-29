[TOC]



# Servlet

Servlet是JAVAWeb三大组件之一。分别是Filer过滤器，Servlet程序，Listener监听器



是运行在web服务器上的java小程序，可以用来接受客服端的请求，以及响应客户端

需要编写一个类去实现Servlet接口

重点实现Servlet方法

### 部署项目

![image-20200428163951833](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200428163951833.png)

### web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
  <servlet>
  	//类名
    <servlet-name>Servlet</servlet-name>
    //包名加类名
    <servlet-class>com.Karse.servlet.Servlet</servlet-class>
  </servlet>
  <servlet-mapping>
  	//类名
    <servlet-name>Servlet</servlet-name>
    //访问的网址
    //url-pattern表示配置一个请求地址
    //要有/打头
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
</web-app>
```

- url-pattern标签配置没有/打头有错误
- servlet-name标签配置上下要相同
- servlet-class标签配置有误会无法访问，可以在要访问的类里对Servlet右键copy qulifiedname

![image-20200426214401889](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426214401889.png)

- http://  表示http协议
- localhost表示服务器ip
- 8080表示端口号
- /TomcatTest表示工程路径
- /hello 资源路径
- 访问网址后要加入url-pattern配置的内容

![image-20200426215215414](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426215215414.png)



### 生命周期

![image-20200426215617075](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426215617075.png)

### 继承实现Servlet

![image-20200426223647248](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426223647248.png)

![image-20200426224533183](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426224533183.png

![image-20200426231424646](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200426231424646.png))

### eclipse可以快速创建servlet类继承httpservlet

servlet接口方法

init   : 初始化方法

  		一定要调用supper.init方法

servlet   ：业务房屋（每次请求都会调用）

destroy   ：销毁方法（web工程停止的时候）



```
public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 执行初始化
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	message = "hello world";
    }
    
    
	/**
	 * get请求的时候调用
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 设置响应内容类型
		response.setContentType("text/html");
		
		//实际逻辑在这
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
	}
		

	/**
	 * post请求的时候调用
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("这是post方法");
	}

	
	@Override
	public void destroy() {
		// 什么也不做
	}
}
```



### ServletConfig类

servlet程序的配置信息类

#### 三大作用

- 可以获取Servlet-name 标签给Servlet配置的别名
- 获取Servlet程序的初始化参数  <init-param>
- 获取ServletContext对象



### GET 和 POST方法

#### GET 方法

是默认的从浏览器向 Web 服务器传递信息的方法，它会产生一个很长的字符串，出现在浏览器的地址栏中。如果您要向服务器传递的是密码或其他的敏感信息，请不要使用 GET 方法。

- 参数出现在请求行中，url后
- 请求的url长度有限制
- 不太安全

#### POST方法

另一个向后台程序传递信息的比较可靠的方法是 POST 方法。POST 方法打包信息的方式与 GET 方法基本相同，但是 POST 方法不是把信息作为 URL 中 ? 字符后的文本字符串进行发送，而是把这些信息作为一个单独的消息。消息以标准输出的形式传到后台程序，您可以解析和使用这些标准输出。

- 参数在请求体中
- 请求的url长度没有限制
- 相对安全
- 如果重写了doGet和doPost方法，代码里不能有super方法，会返回错误





### 中文编码

- setCharacterEncoding只是设置字符的编码方式 
- setContentType除了可以设置字符的编码方式还能设置文档内容的类型



tomcat8.0 get方法自动解决中文编码

post方法要response.setContentType("text/html; charset=UTF-8");

```
//设置响应
response.setContentType("text/html; charset=UTF-8");
//创建字符流
PrintWriter pw = response.getWriter();
//打印
pw.println(html)；
```



### 向客户端返回错误

```
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 设置错误代码和原因
	      response.sendError(407, "Need authentication!!!" );
	}
```



### Servlet 编写过滤器Filter

Servlet 过滤器可以动态地拦截请求和响应，以变换或使用包含在请求或响应中的信息。

#### 目的

- 在客户端的请求访问后端资源之前，拦截这些请求。
- 在服务器的响应发送回客户端之前，处理这些响应。

#### 过滤器

- 身份验证过滤器（Authentication Filters）
- 加密过滤器（Encryption Filters）



### Servlet 异常处理

当一个 Servlet 抛出一个异常时，Web 容器在使用了 exception-type 元素的 **web.xml** 中搜索与抛出异常类型相匹配的配置。

您必须在 web.xml 中使用 **error-page** 元素来指定对特定**异常** 或 HTTP **状态码** 作出相应的 Servlet 调用。



### 注解配置

在类上使用@WebServlet注解，进行配置，代替web.xml方法

@WebServlet("资源路径")



### 请求消息

#### 请求行：

GET /index.html HTTP/1.1

方法字段，URL字段，HTTP协议版本组成

#### 常见的请求头：

User-Agent: 可以在服务端获取该头信息，解决浏览器兼容性问题

Referer: 告诉服务器，当前请求从哪里来。可以判断这个请求从哪里来

​				作用：放盗链（视频播放）

​							统计工作

#### 请求体

#### 封装请求消息的请求参数



## Request

1. tomcat服务器会根据请求url中的资源路径，创建对应的类对象，
2. 服务器会创建request和response对象，request对象中封装请求消息数据
3. 服务器将request和response两个对象传递给Service方法，并且调用Service方法
4. 程序员可以通过request获取请求消息数据，通过response对象设置响应消息数据
5. 服务器给浏览器做出响应之前，会从response对象中拿程序员设置的响应消息数据

### 继承体系

doGet中HttpServletRequest继承了Service的ServletRequest，由tomcat创建对象

### 功能

获取请求行数据

例：GET/day14/demo1?name=zhangsan HTTP/1.1

1.获取请求方法：GET

- ​	String getMethod()

2.获取虚拟目录:  /day14

- String getContextPath()

3.获取Servlet路径： /demo1

- String getServletPath()

4.获取get方法请求参数： name=zhangsan

- String getQueryString()

5.获取请求URL： /day14/demo1

- String getRequestURL()
- StringBuffer getRequestURL()       ----长

6.获取协议及版本 :    HTTP/1.1

- String getProtocol()

7.获取客户机的IP地址： 

- String getRemoteAddr()

### 获取请求体数据

只有POST请求方法，才有请求体，在请求体中封装了POST请求的请求参数

步骤：

1.获取流对象：

获取字符流对象

2.获取流对象中的数据

```
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取字符流
		BufferedReader br = request.getReader();
		String line = null;
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
	}
```



### 获取请求参数

![image-20200429093345695](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200429093345695.png)



根据参数名称获取参数值（常用）

```
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用post方法
		//一般get和post只写一份
		this.doPost(request, response);
	}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//获取参数
		String userName = request.getParameter("username");
		System.out.println("post");
		System.out.println(userName);
			
	}
```



注：request.getParameter()，request.getInputStream()，request.getReader()这三种方法是有冲突的，因为流只能读取一次。

```
		//获取参数值数组
		String[] hobbies = request.getParameterValues("hobby");
		for(String hobby : hobbies){
			System.out.println(hobby);
		}
```



通过枚举获取所有请求的参数名称

```
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			//得到标签名字
			String name = parameterNames.nextElement();
			System.out.println(name);
			//得到标签的值
			String value = request.getParameter(name);
			System.out.println(value);
			System.out.println("--------------");
		}
```



通过map集合获取所有数据（常用)

```
		//获取所有参数的map集合
		//键值对，值为数组，因为一个值可能有多个
		java.util.Map<String, String[]> parameterMap =  request.getParameterMap();
		//遍历
		Set<String> keyset = parameterMap.keySet();
		for(String name : keyset){
			
			//获取键获取值
			String[] values = parameterMap.get(name);
			System.out.println(name);
			for(String value : values){
				System.out.println(value);
			}
			
			System.out.println("--------------");
		}
```



### 请求转发

一种在服务器内部的资源跳转方式（用于不同servlet分工协作）

步骤：

1.通过Request对象获取请求转发器对象：getRequestDispatcher

2.使用该对象的forward(request, response)转发

```
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1被访问了");
		//将request response转发给该路径的servlet
		request.getRequestDispatcher("/requestDispatchr2").forward(request, response);
	}
	
	
//第二个Servlet
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("2被访问了");
	}
```

特点：

1.浏览器路径不发生变化

2.只能转发到当前服务器内部资源中，不能转发到外部

3.转发只有一次请求：同时使用一个请求

### 共享数据

域对象：一个有作用范围的对象，可以在范围内共享数据

request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据

方法：

1. void       setAttribute(String name , Object obj)  :存储数据

2.  Object     request.getAttribute("name");      :通过键获取数据



### 获取ServletContext对象

ServletContext      getServletContext()