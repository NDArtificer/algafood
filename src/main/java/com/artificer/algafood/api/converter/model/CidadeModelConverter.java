package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.domain.model.Cidade;

@Component
public class CidadeModelConverter {
	
	
	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade Cidade) {
		return modelMapper.map(Cidade, CidadeModel.class);
	}

	public List<CidadeModel> toColletionModel(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
	}

}
