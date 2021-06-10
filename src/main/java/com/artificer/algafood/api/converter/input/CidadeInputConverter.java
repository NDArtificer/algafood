package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.CidadeInput;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.model.Estado;

@Component
public class CidadeInputConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}

	public CidadeInput toInput(Cidade cidade) {
		return modelMapper.map(cidade, CidadeInput.class);
	}

	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		// * Para mitigar a exception:
		// *org.hibernate.HibernateException: identifier of an instance of com.artificer.algafood.domain.model.Cozinha was altered from 1 to 3
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeInput, cidade);
	}
	

}
