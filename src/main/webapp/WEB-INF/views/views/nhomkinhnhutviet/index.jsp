<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<div class="banner-wrapper bg-light">
    <div id="index_banner" class="banner-vertical-center-index container-fluid pt-5">
        <!-- Start slider -->
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <ol class="carousel-indicators">
                <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
                <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
                <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
					<img class="d-block w-100" src="${contextPath}/assets/img/Slide1.png" alt="First slide">
                </div>
                <div class="carousel-item">
					<img class="d-block w-100" src="${contextPath}/assets/img/Slide2.png" alt="Second slide">

                </div>
                <div class="carousel-item">
					<img class="d-block w-100" src="${contextPath}/assets/img/Slide3.png" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev text-decoration-none" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
                <i class='bx bx-chevron-left'></i>
                <span class="visually-hidden">Previous</span>
            </a>
            <a class="carousel-control-next text-decoration-none" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
                <i class='bx bx-chevron-right'></i>
                <span class="visually-hidden">Next</span>
            </a>
        </div>
        <!-- End slider -->

    </div>
</div>

<!-- Start Service -->
<section class="service-wrapper py-3">
    
    <div class="service-tag py-5 bg-secondary">
        <div class="col-md-12">
            <ul class="nav d-flex justify-content-center">
                <li class="nav-item mx-lg-2">
                    <a class="filter-btn nav-link btn-outline-primary active shadow rounded-pill text-light px-4 light-300" href="#" data-filter=".project">All</a>
                </li>
                <c:forEach items="${listCat}" var="cat">
                	<li class="nav-item mx-lg-2">
	                    <a class="filter-btn nav-link btn-outline-primary rounded-pill text-light px-4 light-300" href="#" data-filter=".cat-${cat.id}">${cat.name}</a>
	                </li>
                </c:forEach>
            </ul>
        </div>
    </div>

</section>

<section class="container overflow-hidden py-5">
    <div class="row gx-5 gx-sm-3 gx-lg-5 gy-lg-5 gy-3 pb-3 projects">
        <!-- Start Recent Work -->
        <c:if test="${not empty listProducts}">
        	<c:forEach items="${listProducts}" var="product">
        		<div class="col-xl-3 col-md-4 col-sm-6 project ui cat-${product.cat.id}">
		            <a href="#" class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
		                <img class="service card-img" src="${contextPath}/assets/img/services-01.jpg" alt="Card image">
		                <div class="service-work-vertical card-img-overlay d-flex align-items-end">
		                    <div class="service-work-content text-left text-light">
		                        <span class="btn btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">${stringUtil.setStringCompact(product.name, 10)}</span>
		                        <p class="card-text">${stringUtil.setStringCompact(product.detail, 20)}</p>
		                    </div>
		                </div>
		            </a>
		        </div>
        	</c:forEach>
        </c:if>
    </div>
</section>
<!-- End Service -->
<section class="py-5 mb-5">
    <div class="container">
        <div class="recent-work-header row text-center pb-5">
            <h2 class="col-md-6 m-auto h2 semi-bold-600 py-5">Bài viết của chúng tôi</h2>
        </div>
        <div class="row gy-5 g-lg-5 mb-4">
        <c:forEach items="${listNews}" var="news">
        	 <div class="col-md-4 mb-3">
                <a href="#" class="recent-work card border-0 shadow-lg overflow-hidden">
                    <img class="recent-work-img card-img" src="${contextPath}/assets/img/recent-work-01.jpg" alt="Card image">
                    <div class="recent-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="recent-work-content text-start mb-3 ml-3 text-dark">
                            <h3 class="card-title light-300 title">${stringUtil.setStringCompact(news.title, 20)}</h3>
                            <p class="card-text">${stringUtil.setStringCompact(news.detail, 20)}</p>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
        </div>
    </div>
</section>