<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Nhôm Kính Nhựt Việt | Admin</title>
    
    <c:url value="/resources/admin" var="adminContextPath" scope="application"/>
    <c:url value="/upload/upload" var="pictureContextPath" scope="application"/>
    
    <link rel="shortcut icon" type="image/ico" href="${adminContextPath}/images/icon-180x180.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="/">
    <!-- Bootstrap -->
    <link href="${adminContextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    
    <link href="${adminContextPath}/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="${adminContextPath}/css/style.css" rel="stylesheet">
    <link href="${adminContextPath}/css/forms.css" rel="stylesheet">
    
    <script src="${adminContextPath}/js/validate.js"></script>
    
    <script src="${adminContextPath}/ckeditor/ckeditor.js"></script>
    <script src="${adminContextPath}/ckfinder/ckfinder.js"></script>
  </head>
  
  <jsp:useBean id="stringUtil" class="spring.util.StringUtil" scope="application"></jsp:useBean>
  <jsp:useBean id="dateUtil" class="spring.util.DateUtil" scope="application"></jsp:useBean>
  <c:url value="/admin/trang-chu.html" var="urlAdminIndex" scope="application"></c:url>
  <c:url value="/admin/danh-muc-san-pham" var="urlAdminCatProduct" scope="application"></c:url>
  <c:url value="/admin/san-pham" var="urlAdminProduct" scope="application"></c:url>
  <c:url value="/admin/danh-muc-tin-tuc" var="urlAdminCatNews" scope="application"></c:url>
  <c:url value="/admin/tin-tuc" var="urlAdminNews" scope="application"></c:url>
  <c:url value="/admin/nguoi-dung" var="urlAdminUser" scope="application"></c:url>
  <c:url value="/admin/lien-he" var="urlAdminContact" scope="application"></c:url>
  <c:url value="/admin/tai-khoan" var="urlProfile" scope="application"></c:url>
  <c:url value="/auth/logout" var="urlLogout" scope="application"></c:url>
  
  <body>
  	<tiles:insertAttribute name="header" />
    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<tiles:insertAttribute name="leftbar" />
		  </div>
		  <tiles:insertAttribute name="body" />
		</div>
    </div>
    <tiles:insertAttribute name="footer" />

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="${adminContextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${adminContextPath}/js/bootstrap-select.min.js"></script>
    <script src="${adminContextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${adminContextPath}/js/jquery.validate.min.js"></script>
    <script src="${adminContextPath}/js/validate.js"></script>
    <script src="${adminContextPath}/js/custom.js"></script>
    <script src="${adminContextPath}/js/solution.js"></script>
  </body>
</html>