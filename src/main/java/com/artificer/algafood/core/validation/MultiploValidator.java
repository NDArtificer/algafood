package com.artificer.algafood.core.validation;

import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

	private int multipleNumber;

	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.multipleNumber = constraintAnnotation.number();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {

		boolean valid = false;

		if (value != null) {
			var valorDecimal = BigDecimal.valueOf(value.doubleValue());
			var valorMultiple = BigDecimal.valueOf(this.multipleNumber);
			var resto = valorDecimal.remainder(valorMultiple);

			valid = BigDecimal.ZERO.compareTo(resto) == 0;
		}

		return valid;
	}

}
