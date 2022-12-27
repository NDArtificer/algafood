package com.artificer.algafood.api.converter.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.EstadoController;
import com.artificer.algafood.api.model.EstadoModel;
import com.artificer.algafood.domain.model.Estado;

@Component
public class EstadoModelConverter extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;

	public EstadoModelConverter() {
		super(EstadoController.class, EstadoModel.class);
	}

	@Override
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = modelMapper.map(estado, EstadoModel.class);

		estadoModel.add(linkTo(methodOn(EstadoController.class).listar()).withRel("estados"));
		estadoModel.add(linkTo(methodOn(EstadoController.class).buscar(estadoModel.getId())).withSelfRel());

		return estadoModel;
	}

	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities);
	}

}
