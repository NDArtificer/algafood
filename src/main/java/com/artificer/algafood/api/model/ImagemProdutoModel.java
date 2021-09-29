package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Imagem")
@Getter
@Setter
public class ImagemProdutoModel extends RepresentationModel<ImagemProdutoModel> {
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}
