package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.RestauranteController;
import com.artificer.algafood.api.model.RestauranteResumoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Restaurante;

@Component
public class RestauranteResumoModelConverter
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResumoModel> {

	public RestauranteResumoModelConverter() {
		super(RestauranteController.class, RestauranteResumoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	@Override
	public RestauranteResumoModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteResumoModel.class)
				.add(apiLinks.linkToRestaurante(restaurante.getId()));
	}

	@Override
	public CollectionModel<RestauranteResumoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities);
	}

}
