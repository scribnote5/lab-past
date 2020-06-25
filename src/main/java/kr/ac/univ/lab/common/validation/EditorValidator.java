package kr.ac.univ.lab.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kr.ac.univ.lab.common.util.CommonUtil;

public class EditorValidator implements ConstraintValidator<Editor, String> {
	long max;

	@Override
	public void initialize(Editor editor) {
		max = editor.max();
	}

	@Override
	public boolean isValid(String str, ConstraintValidatorContext cxt) {
		return CommonUtil.getByteSize(str) < max;
	}
}