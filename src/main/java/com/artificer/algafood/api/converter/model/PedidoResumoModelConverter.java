package com.artificer.algafood.api.converter.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.PedidosController;
import com.artificer.algafood.api.controller.RestauranteController;
import com.artificer.algafood.api.controller.UsuarioController;
import com.artificer.algafood.api.model.PedidoResumoModel;
import com.artificer.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelConverter extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	@Autowired
	private ModelMapper modelMapper;

	public PedidoResumoModelConverter() {
		super(PedidosController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		 var pedidoResumoModel = modelMapper.map(pedido, PedidoResumoModel.class);
		 pedidoResumoModel.add(linkTo(methodOn(PedidosController.class).buscar(pedidoResumoModel.getCodigo())).withSelfRel());
		 pedidoResumoModel.getCliente().add(linkTo(methodOn(UsuarioController.class).buscar(pedidoResumoModel.getCliente().getId())).withRel("Usuario"));
		 pedidoResumoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class).buscar(pedidoResumoModel.getRestaurante().getId())).withRel("Restaurante"));
		 return pedidoResumoModel;
	}

	@Override
	public CollectionModel<PedidoResumoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
		return super.toCollectionModel(entities);
	}
	
}
