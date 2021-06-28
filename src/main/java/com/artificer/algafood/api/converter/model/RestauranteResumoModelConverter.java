package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.RestauranteResumoModel;
import com.artificer.algafood.domain.model.Restaurante;

@Component
public class RestauranteResumoModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public RestauranteResumoModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteResumoModel.class);
	}

	public List<RestauranteResumoModel> toColletionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}

}
