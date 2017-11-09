<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/all.css" type="text/css" />
		<title>JSP的练习页面</title>
		<style type="text/css">
			span{
				color:red;
				font-weight: 900;
			}
			div{
				border:10px double #fff;
				width:500px;
			}
		</style>
	</head>
	<body>
		<%
		//获取日期对象
		Date date =  new Date();
		//设置时间显示格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		//格式化日期
		String today = sdf.format(date);
		%>
		<p>打开页面的系统时间是：<%=today %></p><!-- 输出系统当前打开页面的时间 -->
		<hr />
		<h2>JSP指令</h2>
		<p>JSP指令使用&lt;%@ 指令名 属性="属性值" %&gt;来声明。</p>
		<p>举例:&lt;%@ page language="java" %&gt; </p>
		<p>页面指令page其作用是定义与整个jsp页面相关的属性，比如jsp的编码、内容类型及引用的类库。</p>
		<P>include指令：它可以将一个JSP页面包含到另一个JSP页面中实现JSP的重用，如下图所示。</P>
		<img src="./images/include指令过程.png" width="600px" height="400px" style="opacity:0.6" />
		<p>include指令是静态包含，即被包含文件中的所有内容会被原样包含到该JSP页面中即使被包含的文件中有JSP代码，在包含时也不会编译执行</p>
		<P><span>而是在将两个页面组合成一个页面之后再去编译执行的，</span>最后返回结果页面。</P>
		<P>taglib指令：用于声明一个标签的引用，在JSP页面之中声明了那个标签库的引用，即可在JSP页面调用那个标签。如：JSTL</P>
		<P>&lt;%JSP脚本%&gt;<span>--||--</span>&lt;%=JSP表达式%&gt;<span>--||--</span>&lt;%!JSP声明标识%&gt;</P>
		<h2>JSP动作</h2>
		<p><span>include动作：</span>动作标识用于包含其他页面。被包含页面可以是动态或者静态页面。&lt;jsp:include&gt;</p>
		<p>原理是将包含的页面编译处理后将结果包含在页面中，包含页面的过程如下图所示</p>
		<img src="./images/include动作过程.png" width="600px" height="400px" style="opacity:0.6" /><br />
		<span>当浏览器第一次请求第一个使用&lt;jsp:include&gt;包含其他页面时，Web容器首先编译被包含的页面。然后将编译处理后的返回结果包含在页面中，之后编译页面，最后将页面的组合结果回应给浏览器</span>
		<div>
			<p>&lt;jsp:include page="url" flush="false|true" /&gt;</p>
			<p>或者</p>
			<p>&lt;jsp:include page="url" flush="false|true"&gt;</p>
			<p>&lt;jsp:param 属性="属性名"&gt;//子动作标识设置参数</p>
			<P>&lt;/jsp:include&gt;</P>
		</div>
		<br />
		<div>
			<P>使用请求转发的动作标识&lt;jsp:forward&gt;</P>
			<P>&lt;jsp:forward  page="url" /&gt;</P>
			<p>或者</p>
			<P>&lt;jsp:forward page="url"&gt;</P>
			<P>&lt;jsp:param&gt;</P>
			<P>&lt;/jsp:forward&gt;</P>
			<span>请求转发是服务器要的操作，浏览器并不会知道请求的页面，所以浏览的地址栏不会变。</span>
		</div>
		<span>&lt;jsp:useBean&gt;动作标识</span><br />
		<img src="./images/useBean动作规范.png" width="600px" height="400px" style="opacity:0.6" /><br />
		<%--<jsp:forward page="jspdemo2.jsp"></jsp:forward>--%>
		<a href="jspdemo2.jsp?name=222&age=111">跳转向下一个页面</a>
		<%-- 这里的参数被第二个jsp页面的javabean动作捕捉到并赋值给jsp页面中的bean类对象，进行了自动匹配 --%>
	</body>
</html>