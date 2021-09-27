package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.RestauranteController;
import com.artificer.algafood.api.model.RestauranteSummaryModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Restaurante;

@Component
public class RestauranteSummaryModelConverter
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteSummaryModel> {

	public RestauranteSummaryModelConverter() {
		super(RestauranteController.class, RestauranteSummaryModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	@Override
	public RestauranteSummaryModel toModel(Restaurante restaurante) {
		RestauranteSummaryModel restauranteSummaryModel = modelMapper.map(restaurante, RestauranteSummaryModel.class);
		restauranteSummaryModel.add(apiLinks.linkToRestaurante(restauranteSummaryModel.getId()));
		restauranteSummaryModel.add(apiLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "Forma Pagamento"));
		restauranteSummaryModel.add(apiLinks.linkToResponsaveisRestaurante(restaurante.getId(), "Responsaveis"));
		restauranteSummaryModel.getCozinha().add(apiLinks.linkToCozinha(restauranteSummaryModel.getCozinha().getId()));

		if (restaurante.getAtivo()) {
			restauranteSummaryModel.add(apiLinks.linkToInactiveRestaurante(restaurante.getId(), "Inativar"));
		} else {
			restauranteSummaryModel.add(apiLinks.linkToActiveRestaurante(restaurante.getId(), "Ativar"));
		}

		if (restaurante.getAberto()) {
			restauranteSummaryModel.add(apiLinks.linkToCloseRestaurante(restaurante.getId(), "Fechar"));
		} else {
			restauranteSummaryModel.add(apiLinks.linkToOpenRestaurante(restaurante.getId(), "Abrir"));
		}

		return restauranteSummaryModel;
	}

	@Override
	public CollectionModel<RestauranteSummaryModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities);
	}

}
