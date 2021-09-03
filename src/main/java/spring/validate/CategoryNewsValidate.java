package spring.validate;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.model.Category_new;
import spring.service.CategoryNewService;

@Component
public class CategoryNewsValidate implements Validator {

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
		Category_new category = (Category_new) object;
		if (categoryNewService.findByName(category.getName()) != null) {
			errors.rejectValue("name", null, messageSource.getMessage("duplicateCategory", null, Locale.getDefault()));
		}
	}

}
