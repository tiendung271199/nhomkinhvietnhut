package spring.controller.admin;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.User;
import spring.service.UserService;
import spring.util.FileUtil;
import spring.validate.UserValidate;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminProfileController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidate userValidate;

	@GetMapping(URLConstant.URL_ADMIN_PROFILE)
	public String profile(Model model, HttpSession session) {
		User user = (User) session.getAttribute("adminUserLogin");
		if (user == null) {
			return "redirect:/" + URLConstant.URL_ADMIN_LOGIN;
		}
		model.addAttribute("user", user);
		return ViewNameConstant.ADMIN_PROFILE;
	}

	@PostMapping(URLConstant.URL_ADMIN_PROFILE)
	public String update(@Valid @ModelAttribute("userError") User user, BindingResult rs,
			@RequestParam("picture") MultipartFile multipartFile,
			@RequestParam(required = false) String confirmPassword, Model model, RedirectAttributes ra,
			HttpServletRequest request, HttpSession session) {
		User userLogin = (User) session.getAttribute("adminUserLogin");
		user.setId(userLogin.getId());
		userValidate.validatePhone(user, rs);
		userValidate.validatePicture(multipartFile.getOriginalFilename(), rs);
		if (!user.getPassword().equals(GlobalConstant.EMPTY)) {
			userValidate.validatePassword(user, rs, userLogin, confirmPassword, model);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		} else {
			user.setPassword(userLogin.getPassword());
		}
		if (rs.hasErrors()) {
			model.addAttribute("user", user);
			return ViewNameConstant.ADMIN_PROFILE;
		}
		String fileName = FileUtil.uploadFile(multipartFile, request, GlobalConstant.DIR_UPLOAD_AVATAR);
		user.setAvatar(fileName);
		if (fileName.equals(GlobalConstant.EMPTY)) {
			user.setAvatar(userLogin.getAvatar());
		}
		if (userService.update(user) > 0) {
			if (!fileName.equals(GlobalConstant.EMPTY)) {
				FileUtil.delFile(userLogin.getAvatar(), request, GlobalConstant.DIR_UPLOAD_AVATAR);
			}
			userLogin = userService.findById(userLogin.getId());
			session.setAttribute("adminUserLogin", userLogin);
			ra.addFlashAttribute("success",
					messageSource.getMessage("updateProfileSuccess", null, Locale.getDefault()));
		} else {
			if (!fileName.equals(GlobalConstant.EMPTY)) {
				FileUtil.delFile(fileName, request, GlobalConstant.DIR_UPLOAD_AVATAR);
			}
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_PROFILE_REDIRECT;
	}

}
