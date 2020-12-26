package com.dch.core.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test {@link ValidationUtils}.
 *
 * @author Sayid Sidqi
 * @version 2.0.0
 * @since 2.0.0
 */
public class ValidationUtilsTest {

    @BeforeAll
    public static void setUpOnce() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        ValidationUtils.setValidator(validatorFactory.getValidator());
    }

    @Test
    public void testValidate_withValidModel_thenExpectNoErrorThrown() {
        Model model = new Model("ten", 10);
        assertThatCode(() -> ValidationUtils.validate(model)).doesNotThrowAnyException();
    }

    @Test
    public void testValidate_withNewValidator_thenExpectValidatorIsIgnored() {
        Model model = new Model("ten", 10);
        ValidationUtils.setValidator(Validation.buildDefaultValidatorFactory().getValidator());
        assertThatCode(() -> ValidationUtils.validate(model)).doesNotThrowAnyException();
    }

    @Test
    public void testValidate_withInvalidModel_throwConstraintViolationException() {
        Model model = new Model(" ", -1);
        assertThrows(ConstraintViolationException.class, () -> ValidationUtils.validate(model));
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