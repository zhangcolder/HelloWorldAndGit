<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单上传文件测试</title>
<link type="text/css" href="css/all.css" rel="stylesheet" />
</head>
<body>
	<!-- enctype="multipart/form-data" 表示提交的数据是二进制文件 -->
	<!-- 下一个页面将显示上传的图片，但是图片不会真正的上传的服务器 -->
	<!-- 图片的是直接同过io流读取后，再用Output将数据输出到页面显示的 -->
	<!-- 可以做一个，用户上传头像的预览功能 -->
	<form action="UploadPhoto" method="post" enctype="multipart/form-data">
		玩家昵称：<input type="text" name="heroName" /><br /><br />
		玩家头像:<input type="file" name="filepath" /><br /><br />
		<input type="submit" value="上传" />
	</form>
</body>
</html>