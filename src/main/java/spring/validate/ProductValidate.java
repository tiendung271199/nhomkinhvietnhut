package spring.validate;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import spring.constant.GlobalConstant;
import spring.service.CategoryService;
import spring.util.FileUtil;

@Component
public class ProductValidate implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object object, Errors errors) {

	}

	public void validateCat(int catId, Errors errors) {
		if (catId == 0) {
			errors.rejectValue("cat", null, messageSource.getMessage("NoCatSelected", null, Locale.getDefault()));
		} else {
			if (categoryService.findById(catId) == null) {
				errors.rejectValue("cat", null, messageSource.getMessage("CatNotExists", null, Locale.getDefault()));
			}
		}
	}

	public boolean validatePicture(Model model, List<MultipartFile> multipartFileList) {
		if (multipartFileList.size() > 0) {
			for (MultipartFile multipartFile : multipartFileList) {
				String fileName = multipartFile.getOriginalFilename();
				if (fileName.equals(GlobalConstant.EMPTY)) {
					model.addAttribute("pictureError",
							messageSource.getMessage("NoPictureSelected", null, Locale.getDefault()));
					return true;
				}
				if (!FileUtil.checkFileExtension(fileName)) {
					model.addAttribute("pictureError",
							messageSource.getMessage("formatPictureError", null, Locale.getDefault()));
					return true;
				}
			}
		} else {
			model.addAttribute("pictureError",
					messageSource.getMessage("NoPictureSelected", null, Locale.getDefault()));
			return true;
		}
		return false;
	}
}
