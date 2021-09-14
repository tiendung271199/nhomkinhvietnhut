<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<div id="work_banner" class="banner-wrapper bg-light w-100 py-5">
    <div class="banner-vertical-center-work container text-light d-flex justify-content-center align-items-center py-5 p-0">
        <div class="banner-content col-lg-8 col-12 m-lg-auto text-center">
            <h1 class="banner-heading h2 display-3 pb-5 semi-bold-600 typo-space-line-center">Sản Phẩm</h1>
            <h3 class="h4 pb-2 regular-400">Sản phẩm công ty Nhôm Kính Nhựt Việt</h3>
            <p class="banner-body pb-2 light-300">
                Mô tả doanh nghiệp hoặc sản phẩm của doanh nghiệp
            </p>
            <button type="submit" class="btn rounded-pill btn-outline-light px-4 me-4 light-300">Xem thêm</button>
            <button type="submit" class="btn rounded-pill btn-secondary text-light px-4 light-300">Liên hệ</button>
        </div>
    </div>
</div>

<!-- Start Our Work -->
<section class="container py-5">
    <div class="row justify-content-center my-5">
        <div class="filter-btns shadow-md rounded-pill text-center col-auto">
            <a class="filter-btn btn rounded-pill btn-outline-primary border-0 m-md-2 px-md-4 active" data-filter=".project" href="#">Tất cả</a>
            <c:forEach items="${listCat}" var="cat">
            	<a class="filter-btn btn rounded-pill btn-outline-primary border-0 m-md-2 px-md-4" data-filter=".danh-muc-${cat.id}" href="#">${cat.name}</a>
            </c:forEach>
        </div>
    </div>

    <div class="row projects gx-lg-5">
    	<c:if test="${not empty listProduct}">
    		<c:forEach items="${listProduct}" var="product">
    			<a href="work-single.html" class="col-sm-6 col-lg-4 text-decoration-none project marketing social danh-muc-${product.cat.id}">
		            <div class="service-work overflow-hidden card mb-5 mx-5 m-sm-0">
		                <img class="card-img-top" src="${contextPath}/assets/img/our-work-01.jpg" alt="...">
		                <div class="card-body">
		                    <h5 class="card-title light-300 text-dark">${product.name}</h5>
		                    <p class="card-text light-300 text-dark">
		                        ${stringUtil.setStringCompact(product.detail, 20)}
		                    </p>
		                    <span class="text-decoration-none text-primary light-300">
		                          Read more <i class='bx bxs-hand-right ms-1'></i>
		                      </span>
		                </div>
		            </div>
		        </a>
    		</c:forEach>
    	</c:if>
        
    </div>
    <div class="row">
        <div class="btn-toolbar justify-content-center pb-4" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="First group">
                <button type="button" class="btn btn-secondary text-white">Previous</button>
            </div>
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button type="button" class="btn btn-light">1</button>
            </div>
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button type="button" class="btn btn-secondary text-white">2</button>
            </div>
            <div class="btn-group" role="group" aria-label="Third group">
                <button type="button" class="btn btn-secondary text-white">Next</button>
            </div>
        </div>
    </div>
</section>
<!-- End Our Work -->

<section class="bg-light py-5">
    <div class="feature-work container my-4">
        <div class="row d-flex d-flex align-items-center">
            <div class="col-lg-5">
                <h3 class="feature-work-title h4 text-muted light-300">Featured Work</h3>
                <h1 class="feature-work-heading h2 py-3 semi-bold-600">Transform with us</h1>
                <p class="feature-work-body text-muted light-300">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Quis ipsum suspendisse
                    ultrices gravida. Risus commodo viverra maecenas accumsan lacus vel facilisis.
                </p>
                <p class="feature-work-footer text-muted light-300">Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum suspendisse ultrices gravida. Risus commodo viverra maecenas. Duis aute irure dolor in reprehenderit in voluptate velit esse
                    cillum dolore eu fugiat nulla pariatur.</p>
            </div>
            <div class="col-lg-6 offset-lg-1 align-left">
                <div class="row">
                    <a class="col" data-type="image" data-fslightbox="gallery" href="${contextPath}/assets/img/feature-work-1-large.jpg">
                        <img class="img-fluid" src="${contextPath}/assets/img/feature-work-1.jpg">
                    </a>
                    <a class="col" data-type="image" data-fslightbox="gallery" href="${contextPath}/assets/img/feature-work-2-large.jpg">
                        <img class="img-fluid" src="${contextPath}/assets/img/feature-work-2.jpg">
                    </a>
                </div>
                <div class="row pt-4">
                    <a class="col" data-type="image" data-fslightbox="gallery" href="${contextPath}/assets/img/feature-work-3-large.jpg">
                        <img class="img-fluid" src="${contextPath}/assets/img/feature-work-3.jpg">
                    </a>
                    <a class="col" data-type="image" data-fslightbox="gallery" href="${contextPath}/assets/img/feature-work-4-large.jpg">
                        <img class="img-fluid" src="${contextPath}/assets/img/feature-work-4.jpg">
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>