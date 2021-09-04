<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Demo</title>
</head>

<body>
	<h2>View Demo</h2>
	<form action="${pageContext.request.contextPath}/demo" method="post" enctype="multipart/form-data">
		<input type="file" name="picture" multiple="multiple" />
		<input type="submit" value="submit" />
	</form>
	
	${demo}
</body>
</html>