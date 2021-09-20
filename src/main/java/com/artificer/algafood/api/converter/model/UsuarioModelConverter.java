package com.artificer.algafood.api.converter.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.UsuarioController;
import com.artificer.algafood.api.controller.UsuarioGrupoController;
import com.artificer.algafood.api.model.UsuarioModel;
import com.artificer.algafood.domain.model.Usuario;

@Component
public class UsuarioModelConverter extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

	public UsuarioModelConverter() {
		super(UsuarioController.class, UsuarioModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);

		usuarioModel
				.add(linkTo(methodOn(UsuarioController.class).buscar(usuarioModel.getId())).withRel("usuario"));

		usuarioModel
				.add(linkTo(methodOn(UsuarioGrupoController.class).listar(usuario.getId())).withRel("grupos-usuario"));

		return usuarioModel;
	}

	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
	}

}
