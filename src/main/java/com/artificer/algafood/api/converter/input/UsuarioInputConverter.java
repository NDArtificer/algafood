package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.UsuarioInput;
import com.artificer.algafood.domain.model.Usuario;

@Component
public class UsuarioInputConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public UsuarioInput toInput(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioInput.class);
	}

	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}

}
