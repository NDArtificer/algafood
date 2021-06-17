package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.GrupoInput;
import com.artificer.algafood.domain.model.Grupo;

@Component
public class GrupoInputConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomainObject(GrupoInput cidadeInput) {
		return modelMapper.map(cidadeInput, Grupo.class);
	}

	public GrupoInput toInput(Grupo grupo) {
		return modelMapper.map(grupo, GrupoInput.class);
	}

	public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}
	

}
