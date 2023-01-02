package com.artificer.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problem")
public class Problem {

	@Schema(example = "400")
	private Integer status;

	@Schema(example = "http://artificer.com.br/dados-invalidos")
	private String type;

	@Schema(example = "Dados Invalidos")
	private String title;

	@Schema(example = "Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente")
	private String detail;

	@Schema(example = "2022-12-31T12:00:00.123456789Z")
	private OffsetDateTime timeStamp;

	@Schema(example = "Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente")
	private String userMessage;

	@Schema(example = "Lista dos Objetos que geraram erro")
	private List<Object> objects;

	@Getter
	@Builder
	@Schema(name = "ObjectProblem")
	public static class Object {

		@Schema(example = "preco")
		private String name;

		@Schema(example = "preço está inválido")
		private String userMessage;
	}

}
