package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Contact;
import spring.service.ContactService;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.util.bean.Status;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminContactController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ContactService contactService;

	@GetMapping({ URLConstant.URL_ADMIN_CONTACT_INDEX, URLConstant.URL_ADMIN_CONTACT_INDEX_PAGINATION,
			URLConstant.URL_ADMIN_CONTACT_SEARCH, URLConstant.URL_ADMIN_CONTACT_SEARCH_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, @RequestParam(required = false) String keyword,
			@PathVariable(required = false) String keywordURL, Model model, RedirectAttributes ra) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				ra.addFlashAttribute("error", messageSource.getMessage("pageError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_CONTACT_INDEX_REDIRECT;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = contactService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Contact> listContact = contactService.getList(offset, GlobalConstant.TOTAL_ROW);
		if (keywordURL != null) {
			keyword = StringUtil.dashToSpace(keywordURL);
		}
		if (keyword != null) {
			if (keyword.equals(GlobalConstant.EMPTY)) {
				ra.addFlashAttribute("error", messageSource.getMessage("searchError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_CONTACT_INDEX_REDIRECT;
			}
			model.addAttribute("keyword", keyword);
			totalRow = contactService.totalRowByName(keyword);
			totalPage = PageUtil.getTotalPage(totalRow);
			listContact = contactService.searchByName(keyword, offset, GlobalConstant.TOTAL_ROW);
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listContact", listContact);
		return ViewNameConstant.ADMIN_CONTACT_INDEX;
	}

	@PostMapping(URLConstant.URL_ADMIN_CONTACT_STATUS)
	@ResponseBody
	public String updateStatus(@RequestParam int id) {
		Contact contact = contactService.findById(id);
		String result = GlobalConstant.EMPTY;
		if (contact.getStatus() == 1) {
			contact.setStatus(0);
			if (contactService.updateStatus(contact) > 0) {
				result = new Gson().toJson(new Status("deactive.gif", "Chưa xử lý"));
			}
		} else {
			contact.setStatus(1);
			if (contactService.updateStatus(contact) > 0) {
				result = new Gson().toJson(new Status("active.gif", "Đã liên hệ"));
			}
		}
		return result;
	}

	@GetMapping(URLConstant.URL_ADMIN_CONTACT_DEL)
	public String del(@PathVariable int id, RedirectAttributes ra) {
		Contact contact = contactService.findById(id);
		if (contact == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataContact", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CONTACT_INDEX_REDIRECT;
		}
		if (contactService.del(id) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("delContactSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_CONTACT_INDEX_REDIRECT;
	}

}
