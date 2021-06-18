package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.UsuarioModel;
import com.artificer.algafood.domain.model.Usuario;

@Component
public class UsuarioModelConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}

	public List<UsuarioModel> toColletionModel(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}
	

}
