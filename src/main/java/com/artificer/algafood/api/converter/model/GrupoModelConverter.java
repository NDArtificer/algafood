package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.GrupoController;
import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Grupo;

@Component
public class GrupoModelConverter extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

	public GrupoModelConverter() {
		super(GrupoController.class, GrupoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinks apiLinks;
	@Override
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel grupoModel = modelMapper.map(grupo, GrupoModel.class);
		
		grupoModel.add(apiLinks.linkToGrupo(grupo.getId()));
		grupoModel.add(apiLinks.linkToGrupoPermissoes(grupo.getId(), "Permissoes"));
		
		
		return grupoModel;
	}

	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
		return super.toCollectionModel(entities);
	}
	
}
