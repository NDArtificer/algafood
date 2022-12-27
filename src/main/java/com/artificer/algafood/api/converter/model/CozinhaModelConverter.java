package com.artificer.algafood.api.converter.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.CozinhaController;
import com.artificer.algafood.api.model.CozinhaModel;
import com.artificer.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelConverter extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaModelConverter() {
		super(CozinhaController.class, CozinhaModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = modelMapper.map(cozinha, CozinhaModel.class);

		cozinhaModel.add(linkTo(CozinhaController.class).withSelfRel());

		return cozinhaModel;
	}

	@Override
	public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities);
	}

}
