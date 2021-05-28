package com.artificer.algafood.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {FreteZeroIncluiDescricaoValidator.class})
public @interface FreteZeroIncluiDescricao {
	
	
	String message() default "Decrição Obrigatória inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String valorField();
	
	String descriptionField();
	
	String descriptionRequired();

}
