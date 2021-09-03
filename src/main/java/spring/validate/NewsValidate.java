package spring.validate;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import spring.constant.GlobalConstant;
import spring.service.CategoryNewService;
import spring.util.FileUtil;

@Component
public class NewsValidate implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryNewService categoryNewService;

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object object, Errors errors) {

	}

	public void validateCat(int catId, Errors errors) {
		if (catId == 0) {
			errors.rejectValue("cat", null, messageSource.getMessage("NoCatNewsSelected", null, Locale.getDefault()));
		} else {
			if (categoryNewService.findById(catId) == null) {
				errors.rejectValue("cat", null, messageSource.getMessage("CatNotExists", null, Locale.getDefault()));
			}
		}
	}

	public void validatePicture(Errors errors, MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			if (!FileUtil.checkFileExtension(fileName)) {
				errors.rejectValue("picture", null,
						messageSource.getMessage("formatPictureError", null, Locale.getDefault()));
			}
		} else {
			errors.rejectValue("picture", null,
					messageSource.getMessage("NoPictureSelected", null, Locale.getDefault()));
		}
	}

	public void validatePictureForUpdate(Errors errors, MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			if (!FileUtil.checkFileExtension(fileName)) {
				errors.rejectValue("picture", null,
						messageSource.getMessage("formatPictureError", null, Locale.getDefault()));
			}
		}
	}

}
