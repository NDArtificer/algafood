package com.artificer.algafood.core.validation;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class FreteZeroIncluiDescricaoValidator implements ConstraintValidator<FreteZeroIncluiDescricao, Object> {

	String valorField;
	String descriptionField;
	String descriptionRequired;

	@Override
	public void initialize(FreteZeroIncluiDescricao constraintAnnotation) {
		this.valorField = constraintAnnotation.valorField();
		this.descriptionField = constraintAnnotation.descriptionField();
		this.descriptionRequired = constraintAnnotation.descriptionRequired();
	}

	@Override
	public boolean isValid(Object valueObject, ConstraintValidatorContext context) {
		boolean valid = true;

		try {
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(valueObject.getClass(), valorField)
					.getReadMethod().invoke(valueObject);

			String description = (String) BeanUtils.getPropertyDescriptor(valueObject.getClass(), descriptionField)
					.getReadMethod().invoke(valueObject);

			if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && description != null) {
				valid = description.toLowerCase().contains(this.descriptionRequired.toLowerCase());

			}

			return valid;

		} catch (Exception e) {
			throw new ValidationException(e);

		}
	}
}
