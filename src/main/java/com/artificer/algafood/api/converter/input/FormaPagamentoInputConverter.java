package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.FormaPagamentoInput;
import com.artificer.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public FormaPagamento toDomainModel(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoInput, formaPagamento);
	}
	

}
