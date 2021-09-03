package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.News;
import spring.service.CategoryNewService;
import spring.service.NewsService;
import spring.util.PageUtil;
import spring.util.StringUtil;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminNewsController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NewsService newsService;

	@Autowired
	private CategoryNewService categoryNewService;

	@GetMapping({ URLConstant.URL_ADMIN_NEWS_INDEX, URLConstant.URL_ADMIN_NEWS_INDEX_PAGINATION,
			URLConstant.URL_ADMIN_NEWS_SEARCH, URLConstant.URL_ADMIN_NEWS_SEARCH_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, @RequestParam(required = false) String keyword,
			@PathVariable(required = false) String keywordURL, Model model, RedirectAttributes ra) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = newsService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<News> listNews = newsService.getList(offset, GlobalConstant.TOTAL_ROW);
		if (keywordURL != null) {
			keyword = StringUtil.dashToSpace(keywordURL);
		}
		if (keyword != null) {
			if (keyword.equals(GlobalConstant.EMPTY)) {
				ra.addFlashAttribute("error", messageSource.getMessage("searchError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
			}
			model.addAttribute("keyword", keyword);
			totalRow = newsService.totalRowByTitle(keyword);
			totalPage = PageUtil.getTotalPage(totalRow);
			listNews = newsService.searchByTitle(keyword, offset, GlobalConstant.TOTAL_ROW);
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listNews", listNews);
		return ViewNameConstant.ADMIN_NEWS_INDEX;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_ADD)
	public String add(Model model) {
		model.addAttribute("listCat", categoryNewService.getAll());
		return ViewNameConstant.ADMIN_NEWS_ADD;
	}
	
	

}
