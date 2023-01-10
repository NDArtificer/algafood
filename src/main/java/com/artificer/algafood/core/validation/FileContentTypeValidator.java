package com.artificer.algafood.core.validation;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> mediaType;

	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.mediaType = Arrays.asList(constraintAnnotation.allowed());
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null || this.mediaType.contains(value.getContentType());
	}

}
