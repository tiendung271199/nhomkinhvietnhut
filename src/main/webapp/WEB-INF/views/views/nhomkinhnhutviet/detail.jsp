<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<c:if test="${not empty product}">
<div id="work_single_banner" class="bg-light w-100">
    <div class="container-fluid text-light d-flex justify-content-center align-items-center border-0 rounded-0 p-0 py-5">
        <div class="banner-content col-lg-8 m-lg-auto text-center py-5 px-3">
            <h1 class="banner-heading h2 pb-5 typo-space-line-center">Digital Marketing</h1>
            <h3 class="h4 pb-2 light-300">Voluptatem accusantium doloremque</h3>
            <p class="banner-footer light-300">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna
                aliqua. Integer ut ipsum eu libero venenatis euismod.
            </p>
        </div>
    </div>
</div>

<section class="container py-5">

    <div class="row pt-5">
        <div class="worksingle-content col-lg-6 m-auto text-left justify-content-center">
           <div class="col-lg-10">
            <div id="templatemo-slide-link-target" class="card mb-3">
                <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-04.jpg" alt="Card image cap">
            </div>
            <div class="worksingle-slide-footer row">

                <div class="col-1 align-self-center">
                    <a href="#multi-item-example" role="button" data-bs-slide="prev">
                        <i class='bx bxs-chevron-left bx-sm text-dark'></i>
                    </a>
                </div>

                <div id="multi-item-example" class="col-10 carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <div class="row">
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-06.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-06-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-05.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-05-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-04.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-04-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-03.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-03-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-01.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-01-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!--/.First slide-->

                        <!--Second slide-->
                        <div class="carousel-item">
                            <div class="row">
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-01.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-01-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-03.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-03-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-02.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-02-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-01.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-01-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-06.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-06-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!--/.Second slide-->

                        <!--Second slide-->
                        <div class="carousel-item">
                            <div class="row">
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-01.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-01-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-03.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-03-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-02.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-02-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-01.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-01-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                                <div class="col">
                                    <a class="templatemo-slide-link" href="${contextPath}/assets/img/work-slide-06.jpg">
                                        <img class="img-fluid border rounded" src="${contextPath}/assets/img/work-slide-06-small.jpg" alt="Product Image">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-1 align-self-center text-end">
                    <a href="#multi-item-example" role="button" data-bs-slide="next">
                        <i class='bx bxs-chevron-right bx-sm text-dark'></i>
                    </a>
                </div>
            </div>
        </div>
        </div>
        <div class="worksingle-content col-lg-6 text-left justify-content-center">
            <h2 class="worksingle-heading h3 pb-3 light-300 typo-space-line">${product.name}</h2>
            <p class="worksingle-footer py-3 text-muted light-300">
                ${product.description}
            </p>
            <div>
            	
            	 <span class="worksingle-heading h6 pb-3 light-200 typo-space-line-2"> 
            	Danh mục: ${product.cat.name}
            	 </span>
            </div>
            
        </div>
    </div>

	<hr />

    <div class="row">
        <div class="col-md-8 m-auto text-left justify-content-center">
            ${product.detail}
        </div>
    </div>
    
</section>
</c:if>
<article class="container-fluid bg-light">
    <div class="container">
        <div class="worksingle-related-header row">
            <h1 class="h2 py-5">Related Post</h1>
            <div class="col-md-12 text-left justify-content-center">
                <div class="row gx-5">
				<c:if test="${not empty listNews}">
					<c:forEach items="${listNews}" var="news">
						 <div class="col-sm-6 col-lg-4 mb-5">
	                        <a href="#" class="related-content card text-decoration-none overflow-hidden h-100">
	                            <img class="related-img card-img-top" src="${contextPath}/assets/img/related-post-01.jpg" alt="Card image cap">
	                            <div class="related-body card-body p-4">
	                                <h5 class="card-title h6 m-0 semi-bold-600 text-dark">${stringUtil.setStringCompact(news.title, 20)}</h5>
	                                <p class="card-text pt-2 mb-1 light-300 text-dark">
	                                    ${stringUtil.setStringCompact(news.detail, 20)}
	                                </p>
	                                <div class="d-flex justify-content-between">
	                                    <span class="text-primary light-300">Read more</span>
	                                    <div class="light-300">
	                                        <i class='bx-fw bx bx-heart me-1'></i>5
	                                        <i class='bx-fw bx bx-chat    ms-1 me-1'></i>3
	                                    </div>
	                                </div>
	                            </div>
	                        </a>
	                    </div>
					</c:forEach>
				</c:if>
                </div>
            </div>
        </div>
    </div>
</article>
