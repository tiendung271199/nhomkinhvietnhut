package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Category;
import spring.model.News;
import spring.model.Product;
import spring.service.CategoryService;
import spring.service.NewsService;
import spring.service.ProductService;
import spring.util.PageUtil;

@Controller
public class NhomKinhController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private NewsService newsService;
	
	@ModelAttribute
	public void setModelCat(Model model) {
		List<Category> listCat = categoryService.getAll();
		model.addAttribute("listCat", listCat);
	}
	
	@GetMapping({URLConstant.URL_INDEX, URLConstant.URL_INDEX_PAGINATION})
	public String index(@PathVariable(required = false) Integer page, Model model) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_INDEX;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = productService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Product> listProducts = productService.getList(offset, GlobalConstant.TOTAL_ROW);
		List<News> listNews = newsService.getListNew();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("listNews", listNews);
		return ViewNameConstant.INDEX;
	}

	@GetMapping(URLConstant.URL_ABOUT)
	public String about() {
		return ViewNameConstant.ABOUT;
	}

}
