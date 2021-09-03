package spring.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
@RequestMapping(URLConstant.URL_ERROR)
public class ErrorController {

	@GetMapping(URLConstant.URL_ERROR_404)
	public String error404() {
		return ViewNameConstant.ERROR_404;
	}

	@GetMapping(URLConstant.URL_ERROR_403)
	public String error403() {
		return ViewNameConstant.ERROR_403;
	}

	@GetMapping(URLConstant.URL_ERROR_400)
	public String error400() {
		return ViewNameConstant.ERROR_400;
	}

}
