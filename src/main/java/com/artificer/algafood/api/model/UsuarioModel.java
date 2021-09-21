package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Usuarios")
@Getter
@Setter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
	
	
	private Long id;
	private String nome;
	private String email;
}
