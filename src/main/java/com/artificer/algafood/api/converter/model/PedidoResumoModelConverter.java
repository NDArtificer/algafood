package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.PedidoResumoModel;
import com.artificer.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoResumoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoModel.class);
	}

	public List<PedidoResumoModel> toColletionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}
	
}
