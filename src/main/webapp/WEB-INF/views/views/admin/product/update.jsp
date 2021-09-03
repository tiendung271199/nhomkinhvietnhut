<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>   
		  <div class="col-md-10">
			  <form id="formProduct" action="" method="post" enctype="multipart/form-data">
	  			  <div class="row">
	  				<div class="col-md-12 panel-info">
			  			<div class="content-box-header panel-heading">
		  					<div class="panel-title ">Cập nhật sản phẩm</div>
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
											<label for="name">Tên sản phẩm</label>
											<form:errors path="productError.name" cssClass="err"/>
											<input type="text" value="${product.name}" name="name" class="form-control" placeholder="Nhập tên sản phẩm">										
										</div>
										<div class="form-group">
											<label for="cat">Danh mục</label>
											<form:errors path="productError.cat" cssClass="err"/>
											<select name="cat.id" class="form-control selectpicker">
												<option value="0">-- Chọn danh mục --</option>
												<c:if test="${not empty listCat}">
													<c:forEach items="${listCat}" var="cat">
														<option <c:if test='${cat.id == product.cat.id}'>selected="selected"</c:if> value="${cat.id}">${cat.name}</option>
													</c:forEach>
												</c:if>
											</select>
										</div>
										<div class="form-group">
											<label for="description">Mô tả sản phẩm</label>
											<form:errors path="productError.description" cssClass="err"/>
											<textarea id="description" name="description" class="form-control" rows="10" >${product.description}</textarea>	
										</div>
									</div>
									<div class="col-sm-12">
										<div class="form-group">
											<label for="detail">Chi tiết sản phẩm</label>
											<form:errors path="productError.detail" cssClass="err"/>
											<textarea id="detail" name="detail" class="form-control" rows="30" >${product.detail}</textarea>									
										</div>
									</div>
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
	document.getElementById("product_management").className = "current";
	var ckeditor = CKEDITOR.replace('detail');
	CKFinder.setupCKEditor(ckeditor, '${pageContext.request.contextPath}/resources/admin/ckfinder/');
</script>	
<script type="text/javascript">
	$('.selectpicker').selectpicker();
</script>