package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.CozinhaModel;
import com.artificer.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaModel toModel(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaModel.class);
	}

	public List<CozinhaModel> toColletionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream().map(cozinha -> toModel(cozinha)).collect(Collectors.toList());
	}

}
