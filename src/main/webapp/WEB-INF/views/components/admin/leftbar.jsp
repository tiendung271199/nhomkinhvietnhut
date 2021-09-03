<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<div class="sidebar content-box" style="display: block;">
	<ul class="nav">
	    <li id="admin-index"><a href="${urlAdminIndex}"><i class="glyphicon glyphicon-home"></i> Trang chủ</a></li>
	    <li id="cat_product_management"><a href="${urlAdminCatProduct}.html"><i class="glyphicon glyphicon-list"></i> Danh mục sản phẩm</a></li>
	    <li id="product_management"><a href="${urlAdminProduct}.html"><i class="glyphicon glyphicon-th-large"></i> Sản phẩm</a></li>
	    <li id="cat_news_management"><a href="${urlAdminCatNews}.html"><i class="glyphicon glyphicon-list"></i> Danh mục tin tức</a></li>
	    <li id="news_management"><a href="${urlAdminNews}.html"><i class="glyphicon glyphicon-globe"></i> Tin tức</a></li>
	    <li id="user_management"><a href="${urlAdminUser}.html"><i class="glyphicon glyphicon-user"></i> Người dùng</a></li>
	    <li id="contact_management"><a href="${urlAdminContact}.html"><i class="glyphicon glyphicon-envelope"></i> Liên hệ</a></li>
	    <li class="submenu">
	         <a href="#">
	            <i class="glyphicon glyphicon-list"></i> Tài khoản
	            <span class="caret pull-right"></span>
	         </a>
	         <!-- Sub menu -->
	         <ul>	  			
	  			<li id="profile_management"><a href="${urlProfile}">Hồ sơ</a></li>
	  			<li><a href="${urlLogout}">Đăng xuất</a></li>
	        </ul>
	    </li>
	</ul>
</div>