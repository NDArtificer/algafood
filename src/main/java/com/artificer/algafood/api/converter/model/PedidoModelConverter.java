package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.PedidoModel;
import com.artificer.algafood.domain.model.Pedido;

@Component
public class PedidoModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoModel.class);
	}

	public List<PedidoModel> toColletionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}
	
}
