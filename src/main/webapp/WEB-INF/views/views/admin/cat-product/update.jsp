<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>   
		  <div class="col-md-10">
			  <form id="formCatDemo" action="" method="post">
	  			  <div class="row">
	  				<div class="col-md-12 panel-info">
			  			<div class="content-box-header panel-heading">
		  					<div class="panel-title ">Cập nhật danh mục sản phẩm</div>
			  			</div>
			  			<div class="content-box-large box-with-header">
				  			<div>
								<div class="row mb-10"></div>
								<c:if test="${not empty error}">
									<div class="alert alert-danger" role="alert">
									    ${error}
									</div>
								</c:if>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="name">Tên danh mục sản phẩm</label>
											<form:errors path="catError.name" cssClass="err"/>
											<input type="text" value="${category.name}" name="name" class="form-control" placeholder="Nhập tên danh mục">										
										</div>
									</div>
									<div class="col-sm-6"></div>
								</div>
								<hr>
								<div class="row">
									<div class="col-sm-12">
										<input type="submit" value="Cập nhật" class="btn btn-success" />
									</div>
								</div>
							</div>
						</div>
			  		</div>
	  			  </div>
	  		  </form>
		  </div>

<script type="text/javascript">
	document.getElementById("cat_product_management").className = "current";
</script>	