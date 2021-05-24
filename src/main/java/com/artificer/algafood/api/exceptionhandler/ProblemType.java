package com.artificer.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	
	MENSAGEM_INCOMPREESIVEL("/mensagem-incompreensivel"," Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/Recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"), 
	PAREMETRO_INVALIDO("/paramentro-invalido", "Parâmetro Inválido"), 
	PROPRIEDADE_DESCONHECIDA("/propriedade-desconhecida","Propriedade Desconhecida"), 
	ERRO_DE_SISTEMA("/erro-de-sistema","Erro de Sistema"), 
	DADOS_INVALIDOS("/dados-ivalidos","Dados inválidos");
	
	private String uri;
	private String title;
	
	ProblemType(String path, String title){
		this.uri ="http://localhost:8080" + path;
		this.title = title;
	}

}
