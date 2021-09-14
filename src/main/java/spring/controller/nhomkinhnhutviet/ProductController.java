package spring.controller.nhomkinhnhutviet;

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
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CategoryService categoryService;
	

	
	@ModelAttribute
	public void setModelCat(Model model) {
		List<Category> listCat = categoryService.getAll();
		model.addAttribute("listCat", listCat);
	}
	
	@GetMapping({URLConstant.URL_PRODUCT, URLConstant.URL_PRODUCT_PAGINATION})
	public String product(@PathVariable(required = false) Integer page, Model model) {
		
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "";  //Trang error
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = productService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Product> listProduct = productService.getList(offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listProduct", listProduct);
		return ViewNameConstant.PRODUCT;
	}

	@GetMapping(URLConstant.URL_PRODUCT_DETAIL)
	public String detail(@PathVariable("id") Integer id, Model model) {
		
		if (productService.findById(id) == null) {
			return ""; //trang error
		}
		
		Product product = productService.findById(id);
		List<News> listNews = newsService.getListNew();
		
		model.addAttribute("product", product);
		model.addAttribute("listNews", listNews);
		return ViewNameConstant.PRODUCT_DETAIL;
	}

}
