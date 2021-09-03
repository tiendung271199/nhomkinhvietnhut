<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav id="main_nav" class="navbar navbar-expand-lg navbar-light bg-secondary shadow">
    <div class="container d-flex justify-content-between align-items-center">
        <a class="navbar-brand h1" href="${urlIndex}">
            <i class='bx bx-buildings bx-sm text-dark'></i>
            <span class="text-dark h4">Nhôm Kính</span> <span class="text-primary h4">Việt Nhựt</span>
        </a>
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-toggler-success" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="navbar-toggler-success">
            <div class="flex-fill mx-xl-5 mb-2">
                <ul class="nav navbar-nav d-flex justify-content-between mx-xl-5 text-center text-dark">
                    <li class="nav-item">
                        <a class="nav-link btn-outline-primary rounded-pill px-3" href="${urlIndex}">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-outline-primary rounded-pill px-3" href="${urlProduct}.html">Sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-outline-primary rounded-pill px-3" href="${urlNews}.html">Tin tức</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-outline-primary rounded-pill px-3" href="${urlAbout}.html">Doanh nghiệp</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-outline-primary rounded-pill px-3" href="${urlContact}.html">Liên hệ</a>
                    </li>
                </ul>
            </div>
            <div class="navbar align-self-center d-flex">
            	
            </div>
        </div>
    </div>
</nav>