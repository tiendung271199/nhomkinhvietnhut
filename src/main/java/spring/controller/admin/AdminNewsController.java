package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.News;
import spring.model.User;
import spring.service.CategoryNewService;
import spring.service.NewsService;
import spring.util.FileUtil;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.validate.NewsValidate;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminNewsController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NewsService newsService;

	@Autowired
	private CategoryNewService categoryNewService;

	@Autowired
	private NewsValidate newsValidate;

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

	@PostMapping(URLConstant.URL_ADMIN_NEWS_ADD)
	public String add(@Valid @ModelAttribute("newsError") News news, BindingResult newsRs,
			@RequestParam("pic") MultipartFile multipartFile, Model model, RedirectAttributes ra,
			HttpServletRequest request) {
		model.addAttribute("listCat", categoryNewService.getAll());
		model.addAttribute("news", news);
		newsValidate.validateCat(news.getCat().getId(), newsRs);
		newsValidate.validatePicture(newsRs, multipartFile);
		if (newsRs.hasErrors()) {
			return ViewNameConstant.ADMIN_NEWS_ADD;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("adminUserLogin");
//		if (user == null) {
//			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
//			return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
//		}
		user = new User(1);
		news.setUser(user);
		String fileName = FileUtil.uploadFile(multipartFile, request, GlobalConstant.DIR_UPLOAD_PICTURE_NEWS);
		news.setPicture(fileName);
		if (newsService.save(news) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("addNewsSuccess", null, Locale.getDefault()));
		} else {
			FileUtil.delFile(fileName, request, GlobalConstant.DIR_UPLOAD_PICTURE_NEWS);
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_DETAIL)
	public String detail(@PathVariable int id, Model model, RedirectAttributes ra) {
		News news = newsService.findById(id);
		if (news == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataNews", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
		}
		model.addAttribute("news", news);
		return ViewNameConstant.ADMIN_NEWS_DETAIL;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_UPDATE)
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		model.addAttribute("listCat", categoryNewService.getAll());
		News news = newsService.findById(id);
		if (news == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataNews", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
		}
		model.addAttribute("news", news);
		return ViewNameConstant.ADMIN_NEWS_UPDATE;
	}

	@PostMapping(URLConstant.URL_ADMIN_NEWS_UPDATE)
	public String update(@Valid @ModelAttribute("newsError") News news, BindingResult newsRs,
			@RequestParam("pic") MultipartFile multipartFile, Model model, RedirectAttributes ra,
			HttpServletRequest request) {
		model.addAttribute("listCat", categoryNewService.getAll());
		model.addAttribute("news", news);
		News oldNews = newsService.findById(news.getId());
		newsValidate.validateCat(news.getCat().getId(), newsRs);
		newsValidate.validatePictureForUpdate(newsRs, multipartFile);
		if (newsRs.hasErrors()) {
			news.setPicture(oldNews.getPicture());
			return ViewNameConstant.ADMIN_NEWS_UPDATE;
		}

		String fileName = multipartFile.getOriginalFilename();
		if (fileName.equals(GlobalConstant.EMPTY)) {
			fileName = oldNews.getPicture();
		} else {
			fileName = FileUtil.uploadFile(multipartFile, request, GlobalConstant.DIR_UPLOAD_PICTURE_NEWS);
			FileUtil.delFile(oldNews.getPicture(), request, GlobalConstant.DIR_UPLOAD_PICTURE_NEWS);
		}
		news.setPicture(fileName);
		if (newsService.update(news) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("updateNewsSuccess", null, Locale.getDefault()));
		} else {
			FileUtil.delFile(fileName, request, GlobalConstant.DIR_UPLOAD_PICTURE_NEWS);
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_DEL)
	public String del(@PathVariable int id, Model model, RedirectAttributes ra, HttpServletRequest request) {
		News news = newsService.findById(id);
		if (news == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataNews", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
		}
		if (newsService.del(id) > 0) {
			FileUtil.delFile(news.getPicture(), request, GlobalConstant.DIR_UPLOAD_PICTURE_NEWS);
			ra.addFlashAttribute("success", messageSource.getMessage("delNewsSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_REDIRECT;
	}

}
