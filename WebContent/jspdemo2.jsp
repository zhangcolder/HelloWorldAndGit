<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/all.css" type="text/css" />
<title>JSP的练习页面</title>
	<style>
		div{
			border:10px double #fff;
			width:500px;
			line-height: 32px;
		}
	</style>
</head>
<body>
	<%-- 在body中，动作标识需要成对存在<……></……>，非body内则使用单标签<……/> --%>
	<h1>javaBean动作的练习</h1>
	<jsp:useBean id="user" class="com.zcolder.bean.JavaBeanTest" scope="request"></jsp:useBean>
	<jsp:setProperty property="*" name="user"/>
	<div>
	setProperty通常情况下和javabean表示一起使用，他调用bean中的 
	setxxx()方法将请求的参数赋值给userbean标识创建的javabean中的简单属性或者索引属性 
	//通过name=“bean实例” 来指定jsp某个范围的bean实例（对象引用） 
	//通关property来匹配请求的参数列表，如果参数中具有和bean实例中相同的属性名（name）,则将参数的中属性值赋给jsp中bean实例的属性  	
	</div>
	<%
		String name = user.getName();
	%>
	name = <%=name %><br />
	age = <jsp:getProperty name="user" property="age"></jsp:getProperty><br />
	<div style="width:1000px">
		JavaBean规范 <br />
		（1）JavaBean 类必须是一个公共类，并将其访问属性设置为 public  ，如： public class user{......}<br />
		（2）JavaBean 类必须有一个空的构造函数：类中必须有一个不带参数的公用构造器<br />
		（3）一个javaBean类不应有公共实例变量，类变量都为private  ，如： private int id;<br />
		（4）属性应该通过一组存取方法（getXxx 和 setXxx）来访问，一般是IDE(Eclipse、JBuilder) 为属性生成getter/setter 方法<br />
		一般JavaBean属性以小写字母开头，驼峰命名格式，相应的 getter/setter 方法是 get/set 接上首字母大写的属性名。例如：属性名为userName，其对应的getter/setter 方法是 getUserName/setUserName。
		但是，还有一些特殊情况：<br />
		1、如果属性名的第二个字母大写，那么该属性名直接用作 getter/setter 方法中 get/set 的后部分，就是说大小写不变。例如属性名为uName，方法是getuName/setuName。<br />
		2、如果前两个字母是大写（一般的专有名词和缩略词都会大写），也是属性名直接用作 getter/setter 方法中 get/set 的后部分。例如属性名为URL，方法是getURL/setURL。<br />
		3、如果首字母大写，也是属性名直接用作 getter/setter 方法中 get/set 的后部分。例如属性名为Name，方法是getName/setName，这种是最糟糕的情况，会找不到属性出错，因为默认的属性名是name。
		所以在JavaBean命名时应该注意符合以上命名规范。
	</div>
	<h2><span>关于servlet生命周期，详见→</span><a href="ServletLive.html">ServletLive.html</a></h2>
	<img src="./images/servlet生命周期.png" width="600px" height="500px" style="opacity:0.7" /><br />
</body>
</html>