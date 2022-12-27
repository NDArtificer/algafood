package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.GrupoPermissaoController;
import com.artificer.algafood.api.model.PermissaoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Permissao;

@Component
public class PermissaoModelConverter extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	public PermissaoModelConverter() {
		super(GrupoPermissaoController.class, PermissaoModel.class);
	}

	public PermissaoModel toModel(Permissao permissao) {
		var permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
		return permissaoModel;
	}

	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities).add(apiLinks.linkToPermissoes());
	}

}
