package com.artificer.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	
	MENSAGEM_INCOMPREESIVEL("/mensagem-incompreensivel"," Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"), 
	PROPRIEDADE_DESCONHECIDA("/propriedade-desconhecida","Propriedade Desconhecida");
	
	private String uri;
	private String title;
	
	ProblemType(String path, String title){
		this.uri ="http://localhost:8080" + path;
		this.title = title;
	}

}