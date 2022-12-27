package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.RestauranteController;
import com.artificer.algafood.api.model.RestauranteModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelConverter extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	public RestauranteModelConverter() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = modelMapper.map(restaurante, RestauranteModel.class);

		restauranteModel.getCozinha().add(apiLinks.linkToCozinha(restaurante.getCozinha().getId()));

		restauranteModel.add(apiLinks.linkToRestauranteProjection("Restaurantes"));
		restauranteModel.add(apiLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "Forma Pagamento"));
		restauranteModel.add(apiLinks.linkToResponsaveisRestaurante(restaurante.getId(), "Responsaveis"));

		if (restauranteModel.getEndereco() != null && restauranteModel.getEndereco().getCidade() != null) {
			restauranteModel.getEndereco().getCidade()
					.add(apiLinks.linkToCidade(restauranteModel.getEndereco().getCidade().getId()));
		}
		if (restaurante.getAtivo()) {
			restauranteModel.add(apiLinks.linkToInactiveRestaurante(restaurante.getId(), "Inativar"));
		} else {
			restauranteModel.add(apiLinks.linkToActiveRestaurante(restaurante.getId(), "Ativar"));
		}

		if (restaurante.getAberto()) {
			restauranteModel.add(apiLinks.linkToCloseRestaurante(restaurante.getId(), "Fechar"));
		} else {
			restauranteModel.add(apiLinks.linkToOpenRestaurante(restaurante.getId(), "Abrir"));
		}

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities);
	}

}
