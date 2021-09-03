package spring.controller.nhomkinhnhutviet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
public class ProductController {

	@GetMapping(URLConstant.URL_PRODUCT)
	public String product() {
		return ViewNameConstant.PRODUCT;
	}

	@GetMapping(URLConstant.URL_PRODUCT_DETAIL)
	public String detail() {
		return ViewNameConstant.PRODUCT_DETAIL;
	}

}
