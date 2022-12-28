package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Cidades")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Santana de Parnaíba")
	private String nome;

	private EstadoModel estado;

}
