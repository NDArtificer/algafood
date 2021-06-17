package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.domain.model.Grupo;

@Component
public class GrupoModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoModel toModel(Grupo grupo) {
		return modelMapper.map(grupo, GrupoModel.class);
	}

	public List<GrupoModel> toColletionModel(List<Grupo> grupos) {
		return grupos.stream().map(grupo -> toModel(grupo)).collect(Collectors.toList());
	}
	
}
