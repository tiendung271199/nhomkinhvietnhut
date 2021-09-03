package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Category_new;
import spring.service.CategoryNewService;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.validate.CategoryNewsValidate;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminCatNewsController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryNewService categoryNewService;

	@Autowired
	private CategoryNewsValidate categoryNewsValidate;

	@GetMapping({ URLConstant.URL_ADMIN_CAT_NEWS_INDEX, URLConstant.URL_ADMIN_CAT_NEWS_INDEX_PAGINATION,
			URLConstant.URL_ADMIN_CAT_NEWS_SEARCH, URLConstant.URL_ADMIN_CAT_NEWS_SEARCH_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, @RequestParam(required = false) String keyword,
			@PathVariable(required = false) String keywordURL, Model model, RedirectAttributes ra) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				ra.addFlashAttribute("error", messageSource.getMessage("pageError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = categoryNewService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Category_new> listCat = categoryNewService.getList(offset, GlobalConstant.TOTAL_ROW);
		if (keywordURL != null) {
			keyword = StringUtil.dashToSpace(keywordURL);
		}
		if (keyword != null) {
			if (keyword.equals(GlobalConstant.EMPTY)) {
				ra.addFlashAttribute("error", messageSource.getMessage("searchError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
			}
			model.addAttribute("keyword", keyword);
			totalRow = categoryNewService.totalRowByName(keyword);
			totalPage = PageUtil.getTotalPage(totalRow);
			listCat = categoryNewService.searchByName(keyword, offset, GlobalConstant.TOTAL_ROW);
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listCat", listCat);
		return ViewNameConstant.ADMIN_CAT_NEWS_INDEX;
	}

	@GetMapping(URLConstant.URL_ADMIN_CAT_NEWS_ADD)
	public String add() {
		return ViewNameConstant.ADMIN_CAT_NEWS_ADD;
	}

	@PostMapping(URLConstant.URL_ADMIN_CAT_NEWS_ADD)
	public String add(@Valid @ModelAttribute("catNewsError") Category_new catNews, BindingResult rs,
			RedirectAttributes ra, Model model) {
		categoryNewsValidate.validate(catNews, rs);
		if (rs.hasErrors()) {
			model.addAttribute("catNews", catNews);
			return ViewNameConstant.ADMIN_CAT_NEWS_ADD;
		}
		if (categoryNewService.save(catNews) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("addCatNewsSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
		}
		model.addAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		return ViewNameConstant.ADMIN_CAT_NEWS_ADD;
	}

	@GetMapping(URLConstant.URL_ADMIN_CAT_NEWS_UPDATE)
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		Category_new catNews = categoryNewService.findById(id);
		if (catNews == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataCat", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
		}
		model.addAttribute("catNews", catNews);
		return ViewNameConstant.ADMIN_CAT_NEWS_UPDATE;
	}

	@PostMapping(URLConstant.URL_ADMIN_CAT_NEWS_UPDATE)
	public String update(@Valid @ModelAttribute("catNewsError") Category_new catNews, BindingResult rs, Model model,
			RedirectAttributes ra) {
		categoryNewsValidate.validate(catNews, rs);
		if (rs.hasErrors()) {
			model.addAttribute("catNews", catNews);
			return ViewNameConstant.ADMIN_CAT_NEWS_UPDATE;
		}
		if (categoryNewService.update(catNews) > 0) {
			ra.addFlashAttribute("success",
					messageSource.getMessage("updateCatNewsSuccess", null, Locale.getDefault()));
			ra.addFlashAttribute("categoryUpdate", catNews.getId()); // để biết được dữ liệu nào vừa được cập nhật
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
		}
		model.addAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		return ViewNameConstant.ADMIN_CAT_NEWS_UPDATE;
	}

	@GetMapping(URLConstant.URL_ADMIN_CAT_NEWS_DEL)
	public String del(@PathVariable int id, RedirectAttributes ra) {
		Category_new catNews = categoryNewService.findById(id);
		if (catNews == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataCat", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
		}
		if (categoryNewService.del(id) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("delCatNewsSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_CAT_NEWS_INDEX_REDIRECT;
	}

}
