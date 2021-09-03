package spring.controller.nhomkinhnhutviet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
public class ContactController {

	@GetMapping(URLConstant.URL_CONTACT)
	public String contact() {
		return ViewNameConstant.CONTACT;
	}
	
}
