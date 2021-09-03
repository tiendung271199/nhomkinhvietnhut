package spring.controller.nhomkinhnhutviet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
public class NewsController {

	@GetMapping(URLConstant.URL_NEWS)
	public String news() {
		return ViewNameConstant.NEWS;
	}

	@GetMapping(URLConstant.URL_NEWS_DETAIL)
	public String detail() {
		return ViewNameConstant.NEWS_DETAIL;
	}
	
}
