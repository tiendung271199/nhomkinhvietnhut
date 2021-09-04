package spring.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
public class AdminAuthController {

	@GetMapping(URLConstant.URL_ADMIN_LOGIN)
	public String login() {
		return ViewNameConstant.AUTH_LOGIN;
	}

}
