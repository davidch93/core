package com.dch.core.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test {@link ValidationUtils}.
 *
 * @author Sayid Sidqi
 * @version 2.0.0
 * @since 2.0.0
 */
public class ValidationUtilsTest {
	
	@BeforeClass
	public static void setUpOnce() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		ValidationUtils.setValidator(validatorFactory.getValidator());
	}
	
	@Test
	public void validate_validModel_noErrorThrown() {
		Model model = new Model("ten", 10);
		assertThatCode(()-> ValidationUtils.validate(model)).doesNotThrowAnyException();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void validate_invalidModel_throwConstraintViolationException() {
		Model model = new Model(" ", -1);
		ValidationUtils.validate(model);
	}
	
	@Getter
	@AllArgsConstructor
	private static class Model {
		
		@NotBlank
		private String nonBlankString;
		
		@Positive
		private Integer positiveInteger;
		
	}
	
}