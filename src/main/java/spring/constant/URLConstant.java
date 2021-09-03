package spring.constant;

public class URLConstant {

	public static final String URL_INDEX = "";
	public static final String URL_INDEX_PAGINATION = "trang-chu/trang-{page}.html";
	public static final String URL_NEWS = "tin-tuc.html";
	public static final String URL_NEWS_DETAIL = "chi-tiet-tin-tuc.html";
	public static final String URL_PRODUCT = "san-pham.html";
	public static final String URL_PRODUCT_DETAIL = "chi-tiet-san-pham.html";
	public static final String URL_CONTACT = "lien-he.html";
	public static final String URL_ABOUT = "doanh-nghiep.html";

	public static final String URL_ADMIN = "admin";
	public static final String URL_ADMIN_INDEX = "trang-chu.html";

	// cat product
	public static final String URL_ADMIN_CAT_PRODUCT_INDEX = "danh-muc-san-pham.html";
	public static final String URL_ADMIN_CAT_PRODUCT_INDEX_PAGINATION = "danh-muc-san-pham/trang-{page}.html";
	public static final String URL_ADMIN_CAT_PRODUCT_SEARCH = "danh-muc-san-pham/tim-kiem/{keywordURL}.html";
	public static final String URL_ADMIN_CAT_PRODUCT_SEARCH_PAGINATION = "danh-muc-san-pham/tim-kiem/{keywordURL}/trang-{page}.html";
	public static final String URL_ADMIN_CAT_PRODUCT_ADD = "danh-muc-san-pham/them-danh-muc.html";
	public static final String URL_ADMIN_CAT_PRODUCT_UPDATE = "danh-muc-san-pham/cap-nhat-danh-muc/{name}-{id}.html";
	public static final String URL_ADMIN_CAT_PRODUCT_DEL = "danh-muc-san-pham/xoa-danh-muc/{name}-{id}.html";

	// product
	public static final String URL_ADMIN_PRODUCT_INDEX = "san-pham.html";
	public static final String URL_ADMIN_PRODUCT_INDEX_PAGINATION = "san-pham/trang-{page}.html";
	public static final String URL_ADMIN_PRODUCT_SEARCH = "san-pham/tim-kiem/{keywordURL}.html";
	public static final String URL_ADMIN_PRODUCT_SEARCH_PAGINATION = "san-pham/tim-kiem/{keywordURL}/trang-{page}.html";
	public static final String URL_ADMIN_PRODUCT_DETAIL = "san-pham/chi-tiet-san-pham/{name}-{id}.html";
	public static final String URL_ADMIN_PRODUCT_PICTURE = "san-pham/hinh-anh-san-pham/{name}-{id}.html";
	public static final String URL_ADMIN_PRODUCT_PICTURE_DELETE = "san-pham/xoa-hinh-anh-san-pham-{id}.html";
	public static final String URL_ADMIN_PRODUCT_ADD = "san-pham/them-san-pham.html";
	public static final String URL_ADMIN_PRODUCT_UPDATE = "san-pham/cap-nhat-san-pham/{name}-{id}.html";
	public static final String URL_ADMIN_PRODUCT_DELETE = "san-pham/xoa-san-pham/{name}-{id}.html";

	// cat news
	public static final String URL_ADMIN_CAT_NEWS_INDEX = "danh-muc-tin-tuc.html";
	public static final String URL_ADMIN_CAT_NEWS_INDEX_PAGINATION = "danh-muc-tin-tuc/trang-{page}.html";
	public static final String URL_ADMIN_CAT_NEWS_SEARCH = "danh-muc-tin-tuc/tim-kiem/{keywordURL}.html";
	public static final String URL_ADMIN_CAT_NEWS_SEARCH_PAGINATION = "danh-muc-tin-tuc/tim-kiem/{keywordURL}/trang-{page}.html";
	public static final String URL_ADMIN_CAT_NEWS_ADD = "danh-muc-tin-tuc/them-danh-muc.html";
	public static final String URL_ADMIN_CAT_NEWS_UPDATE = "danh-muc-tin-tuc/cap-nhat-danh-muc/{name}-{id}.html";
	public static final String URL_ADMIN_CAT_NEWS_DEL = "danh-muc-tin-tuc/xoa-danh-muc/{name}-{id}.html";

	// news
	public static final String URL_ADMIN_NEWS_INDEX = "tin-tuc.html";
	public static final String URL_ADMIN_NEWS_INDEX_PAGINATION = "tin-tuc/trang-{page}.html";
	public static final String URL_ADMIN_NEWS_SEARCH = "tin-tuc/tim-kiem/{keywordURL}.html";
	public static final String URL_ADMIN_NEWS_SEARCH_PAGINATION = "tin-tuc/tim-kiem/{keywordURL}/trang-{page}.html";
	public static final String URL_ADMIN_NEWS_DETAIL = "tin-tuc/chi-tiet-tin-tuc/{name}-{id}.html";
	public static final String URL_ADMIN_NEWS_ADD = "tin-tuc/them-tin-tuc.html";
	public static final String URL_ADMIN_NEWS_UPDATE = "tin-tuc/cap-nhat-tin-tuc/{name}-{id}.html";
	public static final String URL_ADMIN_NEWS_DEL = "tin-tuc/xoa-tin-tuc/{name}-{id}.html";

	// use for redirect
	public static final String URL_ADMIN_CAT_PRODUCT_INDEX_REDIRECT = "admin/danh-muc-san-pham.html";
	public static final String URL_ADMIN_PRODUCT_INDEX_REDIRECT = "admin/san-pham.html";
	public static final String URL_ADMIN_PRODUCT_PICTURE_REDIRECT = "admin/san-pham/hinh-anh-san-pham/{name}-{id}.html";
	public static final String URL_ADMIN_CAT_NEWS_INDEX_REDIRECT = "admin/danh-muc-tin-tuc.html";
	public static final String URL_ADMIN_NEWS_INDEX_REDIRECT = "admin/tin-tuc.html";

	// error
	public static final String URL_ERROR = "error";
	public static final String URL_ERROR_404 = "404";
	public static final String URL_ERROR_403 = "403";
	public static final String URL_ERROR_400 = "400";

}
