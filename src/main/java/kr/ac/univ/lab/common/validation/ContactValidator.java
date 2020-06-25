package kr.ac.univ.lab.common.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactValidator implements ConstraintValidator<Contact, String> {
	@Override
	public void initialize(Contact contact) {
	}

	@Override
	public boolean isValid(String str, ConstraintValidatorContext cxt) {
		return Pattern.compile("(\\d{3})-(\\d{3,4})-(\\d{4})").matcher(str).matches();
	}
}