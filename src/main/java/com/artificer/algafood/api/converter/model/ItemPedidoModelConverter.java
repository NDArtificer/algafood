package com.artificer.algafood.api.converter.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.ItemPedidoModel;
import com.artificer.algafood.domain.model.ItemPedido;

@Component
public class ItemPedidoModelConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ItemPedidoModel toModel(ItemPedido itemPedido) {
		return modelMapper.map(itemPedido, ItemPedidoModel.class);
	}

	public List<ItemPedidoModel> toColletionModel(List<ItemPedido> itemPedidos) {
		return itemPedidos.stream().map(itemPedido -> toModel(itemPedido)).collect(Collectors.toList());
	}
	
}
