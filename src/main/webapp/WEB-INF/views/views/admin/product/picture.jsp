<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
	<div class="col-md-10 panel-info">
		<div class="content-box-header panel-heading">
			<div class="panel-title ">Hình ảnh sản phẩm</div>
		</div>
		<div class="content-box-large box-with-header">
			<form action="" method="post" enctype="multipart/form-data" style="margin-bottom: 30px">
				<div>
					<div class="row mb-10"></div>
					<div class="row">
						<div class="col-sm-6">
							<h3 style="font-size: 20px; color: red; margin-top: 0px">Thêm hình ảnh</h3>
							
							<c:if test="${not empty success}">
								<div class="alert alert-success" role="alert">
								    ${success}
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="alert alert-danger" role="alert">
								    ${error}
								</div>
							</c:if>
							
							<div class="form-group">
								<label for="name">Tên sản phẩm</label>
								<input type="text" name="name" value="${product.name}" class="form-control" readonly="readonly">
							</div>
							
							<div class="form-group">
								<label>Hình ảnh</label>
								<c:if test="${not empty pictureError}"><span class="err">${pictureError}</span></c:if>
								<input type="file" name="picture" class="btn btn-default" multiple="multiple" >
								<p class="help-block"><em>Định dạng: jpg, png, jpeg, gif</em></p>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Thêm" class="btn btn-success" />
						</div>
					</div>
				</div>
			</form>
			<hr >
			<c:if test="${not empty pictureList}">
				<h3 style="font-size: 20px; color: red">Hình ảnh sản phẩm</h3>
				<table class="table table-striped table-bordered" id="example">
					<thead>
						<tr>
							<th width="5%">STT</th>
							<th>Hình ảnh</th>
							<th width="7%">Chức năng</th>
						</tr>
					</thead>
					<tbody>
						<c:set value="0" var="stt" ></c:set>
						<c:forEach items="${pictureList}" var="picture" >
							<tr class="odd gradeA">
								<td>${stt = stt + 1}</td>
								<td>
									<img width="200px" height="120px" src="${pictureContextPath}/product/image/${picture.picture}" />
								</td>
								<td class="center text-center">
                                    <a href="${urlAdminProduct}/xoa-hinh-anh-san-pham-${picture.id}.html" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xoá hình ảnh thứ ${stt} không?')"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row">
					<div class="col-sm-12">
						<input type="button" onclick="back()" value="Quay lại" class="btn btn-success" />
					</div>
				</div>
				<!-- <a href="${urlProduct}" title="" class="btn btn-primary"><i class="fa fa-arrow-circle-left"></i> Quay lại</a> -->
			</c:if>
		</div>
	</div>

<script type="text/javascript">
	document.getElementById("product_management").className = "current";
	
	function back() {
		history.back();
	}
</script>