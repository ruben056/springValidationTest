package be.rd.beans;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContactValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (Contact.class.equals(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "missing.firstname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "missing.lastname");
	}

}
