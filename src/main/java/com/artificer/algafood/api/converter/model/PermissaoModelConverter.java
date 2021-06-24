package com.artificer.algafood.api.converter.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.PermissaoModel;
import com.artificer.algafood.domain.model.Permissao;

@Component
public class PermissaoModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public PermissaoModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}

	public List<PermissaoModel> toColletionModel(Collection<Permissao> permissoes) {
		return permissoes.stream().map(permissao -> toModel(permissao)).collect(Collectors.toList());
	}

}
