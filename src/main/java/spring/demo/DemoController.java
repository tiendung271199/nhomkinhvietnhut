package spring.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DemoController {

	@GetMapping("demo")
	public String demo() {
		return "views/demo/demo";
	}

	@PostMapping("demo")
	public String demo(@RequestParam("picture") List<MultipartFile> list) {
		System.out.println(list.size());
		return "views/demo/demo";
	}
	
}
