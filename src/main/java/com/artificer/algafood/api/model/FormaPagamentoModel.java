package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "FormaPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

	private Long id;
	
	private String descricao;
	
}
