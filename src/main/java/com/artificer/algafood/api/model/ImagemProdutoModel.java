package com.artificer.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagemProdutoModel {
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}
