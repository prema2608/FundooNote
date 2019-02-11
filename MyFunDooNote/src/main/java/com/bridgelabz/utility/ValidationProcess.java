package com.bridgelabz.utility;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgelabz.model.User;

public class ValidationProcess implements Validator {
	static final String REGEX_NAME ="^[a-zA-Z]{3,20}$";
	static final String REGEX_EMAIL_ID = "^[a-z0-9._%-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{3,8}$";
	static final String REGEX_MOBILENUMBER = "^[1-9]{1}[0-9]{9}$";

	@Override
	public boolean supports(Class<?> userClass) {
		return User.class.equals(userClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(!(user.getName().matches(REGEX_NAME)))
		{
			errors.rejectValue("name", "symbolsPresent",new Object[]{"'name'"},"name can't be symbols");
		}
		if(!(user.getEmailId().matches(REGEX_EMAIL_ID)))
		{
			errors.rejectValue("emailId", "symbolsPresent",new Object[]{"'emailId'"},"emailId can't be symbols");
		}
		if(!(user.getPassword().matches(REGEX_PASSWORD)))
		{
			errors.rejectValue("password", "symbolsPresent",new Object[]{"'password'"},"password can't be symbols");
		}
		if(!(String.valueOf(user.getMobileNumber())).matches(REGEX_MOBILENUMBER))
		{
			errors.rejectValue("mobileNumber", "symbolsPresent",new Object[]{"'mobileNumber'"},"mobile number can't be symbols");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId","emailId.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber","mobileNumber.required");
	}

}