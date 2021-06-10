package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.CozinhaInput;
import com.artificer.algafood.domain.model.Cozinha;

@Component
public class CozinhaInputConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainModel(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
	
}
