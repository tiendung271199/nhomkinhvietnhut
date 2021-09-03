<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<c:url value="/resources/upload/picture" var="urlUpload"/>
<div class="col-md-10">
  			<div class="content-box-large">
  				<div class="row">
	  				<div class="panel-heading">
	  					<div class="panel-title ">QUẢN LÝ SẢN PHẨM</div>
		  			</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-8">
						<a href="${urlAdminProduct}/them-san-pham.html" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
					</div>
                	<div class="col-md-4">
                		<form action="${urlAdminProduct}.html" method="get">
		                 	<div class="input-group form">
		                       <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tên sản phẩm">
		                       <span class="input-group-btn">
		                         <button class="btn btn-primary" type="submit">Tìm kiếm</button>
		                       </span>
		                  	</div>
                  		</form>
                  	</div>
				</div>

				<div class="row">
	  				<div class="panel-body">
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
	  					<c:if test="${not empty msg}">
							<div class="alert alert-info" role="alert">
							    ${msg}
							</div>
						</c:if>
		  				<c:choose>
							<c:when test="${not empty listProduct}">
			  					<table class="table table-striped table-bordered" id="example">
									<thead>
										<tr>
											<th width="4%">ID</th>
											<th width="15%">Tên</th>
											<th width="12%">Danh mục</th>
											<th>Mô tả</th>
											<th width="7%">Lượt xem</th>
											<th width="7%">Hình ảnh</th>
											<th width="7%">Chi tiết</th>
											<th width="15%">Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listProduct}" var="product">
											<tr <c:if test='${productUpdate == product.id}'>style="color: red; font-weight: bold"</c:if> class="odd gradeX">
												<td>${product.id}</td>
												<td>${product.name}</td>
												<td>${product.cat.name}</td>
												<td>${product.description}</td>
												<td align="center">${product.views}</td>
												<td align="center">
													<a href="${urlAdminProduct}/hinh-anh-san-pham/${stringUtil.makeSlug(product.name)}-${product.id}.html" title="" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span> Xem</a>
												</td>
												<td align="center">
													<a href="${urlAdminProduct}/chi-tiet-san-pham/${stringUtil.makeSlug(product.name)}-${product.id}.html" title="" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span> Xem</a>
												</td>
												<td class="center text-center">
													<a href="${urlAdminProduct}/cap-nhat-san-pham/${stringUtil.makeSlug(product.name)}-${product.id}.html" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span> Sửa</a>
				                                    <a href="${urlAdminProduct}/xoa-san-pham/${stringUtil.makeSlug(product.name)}-${product.id}.html" onclick="return confirm('Bạn có chắc muốn xoá sản phẩm \'${product.name}\' không?')" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
								<!-- pagination -->
								<nav class="text-center" aria-label="...">
								   <ul class="pagination">
								   	  <c:set value="${currentPage - 1}" var="pagePrevious"></c:set>
								   	  <c:if test="${currentPage == 1}">
								   	  	<c:set value="${currentPage}" var="pagePrevious"></c:set>
								      </c:if>
									  <li <c:if test='${currentPage == 1}'>class="disabled"</c:if>>
									  	<a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${pagePrevious}.html" aria-label="Previous" >
									  		<span aria-hidden="true">«</span>
									  	</a>
									  </li>
																			      
								      <c:choose>
									      <c:when test="${totalPage > 5}">
									      	  <c:if test="${currentPage > 3 and currentPage < (totalPage - 2)}">
									      	  	  <li><a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>.html">Đầu</a></li>
											      <c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
											      <li><a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${totalPage}.html">Cuối</a></li>
										      </c:if>
									      	  <c:if test="${currentPage <= 3}">
											      <c:forEach begin="1" end="5" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
											      <li><a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${totalPage}.html">Cuối</a></li>
										      </c:if>
									      	  <c:if test="${currentPage >= (totalPage - 2)}">
									      	  	  <li><a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>.html">Đầu</a></li>
											      <c:forEach begin="${totalPage - 4}" end="${totalPage}" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
										      </c:if>
									      </c:when>
									      <c:otherwise>
									      	  <c:forEach begin="1" end="${totalPage}" var="page">
										      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
										      	  	  <a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${page}.html">${page}</a>
										      	  </li>
										      </c:forEach>
									      </c:otherwise>
								      </c:choose>
								      
								      <c:set value="${currentPage + 1}" var="pageNext"></c:set>
								      <c:if test="${currentPage == totalPage}">
								      	<c:set value="${currentPage}" var="pageNext"></c:set>
								      </c:if>
									  <li <c:if test='${currentPage == totalPage}'>class="disabled"</c:if>>
									  	<a href="${urlAdminProduct}<c:if test='${not empty keyword}'>/tim-kiem/${stringUtil.spaceToDash(keyword)}</c:if>/trang-${pageNext}.html" aria-label="Next">
									  		<span aria-hidden="true">»</span>
									  	</a>
									  </li>
								   </ul>
								</nav>
								<!-- end pagination -->
							</c:when>
							<c:otherwise>
								<div class="alert alert-info" role="alert">
								  	Không có dữ liệu.
								</div>
							</c:otherwise>
						</c:choose>
	  				</div>
  				</div>
  			</div>
		  </div>
		  
<script type="text/javascript">
	document.getElementById("product_management").className = "current";
</script>