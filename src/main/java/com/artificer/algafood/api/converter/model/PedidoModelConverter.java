package com.artificer.algafood.api.converter.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.CidadeController;
import com.artificer.algafood.api.controller.FormaPagamentoController;
import com.artificer.algafood.api.controller.PedidosController;
import com.artificer.algafood.api.controller.RestauranteController;
import com.artificer.algafood.api.controller.RestauranteProdutosController;
import com.artificer.algafood.api.controller.UsuarioController;
import com.artificer.algafood.api.model.PedidoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Pedido;

@Component
public class PedidoModelConverter extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	public PedidoModelConverter() {
		super(PedidosController.class, PedidoModel.class);

	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	@Override
	public PedidoModel toModel(Pedido pedido) {
		var pedidoModel = modelMapper.map(pedido, PedidoModel.class);

		pedidoModel.add(apiLinks.linkToPedidos());
		pedidoModel.add(linkTo(methodOn(PedidosController.class).buscar(pedidoModel.getCodigo())).withSelfRel());
		pedidoModel.getCliente().add(
				linkTo(methodOn(UsuarioController.class).buscar(pedidoModel.getCliente().getId())).withRel("Usuario"));
		pedidoModel.getRestaurante()
				.add(linkTo(methodOn(RestauranteController.class).buscar(pedidoModel.getRestaurante().getId()))
						.withRel("Restaurante"));
		pedidoModel.getFormaPagamento()
				.add(linkTo(methodOn(FormaPagamentoController.class).buscar(pedidoModel.getFormaPagamento().getId()))
						.withRel("FormaPagamento"));

		pedidoModel.getEndereco().getCidade()
				.add(linkTo(methodOn(CidadeController.class).buscar(pedidoModel.getEndereco().getCidade().getId()))
						.withRel("Cidade"));

		pedidoModel.getItens().forEach(item -> {
			item.add(linkTo(methodOn(RestauranteProdutosController.class).buscar(pedidoModel.getRestaurante().getId(),
					item.getProdutoId())).withRel("Produto"));
		});

		return pedidoModel;
	}

	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
		return super.toCollectionModel(entities).add(linkTo(PedidosController.class).withSelfRel());
	}

}
