# Ajax

向后台请求数据，实现异步



以下是juqery实现ajax

```
//调用jquery包
<script src="js/jquery-1.7.2.min.js"></script>
<script >
	//写到function保证页面加载完成再运行该函数
	funcion  fun(){
		$.ajax({
			url:"ajaxServlet",  //请求路径
			type:"POST",		//请求方式
			data:{"username":"jack","age":23},  //请求参数
			success:function (data){
				alert(data);
			},  //响应成功的回调函数
			error:function (){
				alert
			}
		});
	}
	
</script>
```

```
<script src="js/jquery-1.7.2.min.js"></script>
<script>			
	
	$(function (){	
		//点击按钮执行function函数
		$("#button").click(function (){
			//function里有ajax的get方法
			//参数分别为1.请求的url 2.请求的参数 3.成功执行的function函数
			$.get("ajaxServlet",{username:"rose"},function (data){
				//弹出data数据，data为后台传回的数据
				alert(data);
				//"text"为定义text格式，如传回json要定义json格式
			},"text");
		});	
	});		
	
</script>
```



# Json



```
public void test() throws JsonProcessingException{
		User u = new User();
		u.setEmil("1960073343@qq.com");
		u.setPassword("123");
		
		//2.创建Jackson的核心对象  ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		//3.转换
		/*
		 	转换方法：
		 		writeValue(参数1，obj):
		 			参数1：
		 				File:将obj对象转换成JSON字符串，并保存到指定的文件中
		 				wtiter:将obj对象转换成JSON字符串，并填充到字符输出流
		 				OutputStream:将obj对象转换成JSON字符串，并填充到字节输出流
		 		writeValueAsSrring(obj):将对象转换成json字符串
		 */
		String json = mapper.writeValueAsString(u);
//		System.out.println(json);
		
		
	}
```



若利用json传回实体类对象不想传回某个属性，可以在实体类属性上方添加注解@JsonIgnore

```
	//json传回字符串时忽略该属性
	@JsonIgnore
	private int role; 
```



![image-20200502105923052](C:\Users\Karse\AppData\Roaming\Typora\typora-user-images\image-20200502105923052.png)