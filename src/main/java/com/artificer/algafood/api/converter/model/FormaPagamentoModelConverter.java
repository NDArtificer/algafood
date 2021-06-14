package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.FormaPagamentoModel;
import com.artificer.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelConverter {

	@Autowired
	private ModelMapper modelMapper;



	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}

	public List<FormaPagamentoModel> toColletionModel(List<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream().map(formaPagamento -> toModel(formaPagamento)).collect(Collectors.toList());
	}
	
}
