package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.FormaPagamentoController;
import com.artificer.algafood.api.model.FormaPagamentoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelConverter
		extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	public FormaPagamentoModelConverter() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}

	@Override
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel pagamentoModel = modelMapper.map(formaPagamento, FormaPagamentoModel.class);
		pagamentoModel.add(apiLinks.linkToFormaPagamento(formaPagamento.getId()));
		return pagamentoModel;
	}

	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities);
	}

}
