package com.artificer.algafood.api.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.CidadeController;
import com.artificer.algafood.api.controller.CozinhaController;
import com.artificer.algafood.api.controller.EstadoController;
import com.artificer.algafood.api.controller.FormaPagamentoController;
import com.artificer.algafood.api.controller.PedidoStatusController;
import com.artificer.algafood.api.controller.PedidosController;
import com.artificer.algafood.api.controller.RestauranteController;
import com.artificer.algafood.api.controller.RestauranteFormasPagamentoController;
import com.artificer.algafood.api.controller.RestauranteProdutosController;
import com.artificer.algafood.api.controller.RestauranteUsuarioController;
import com.artificer.algafood.api.controller.UsuarioController;
import com.artificer.algafood.api.controller.UsuarioGrupoController;

@Component
public class ApiLinks {
	
	
	public static final TemplateVariables PROJECTION = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM));

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public Link linkToPedidos(String rel) {

		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("clientid", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteid", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaofim", VariableType.REQUEST_PARAM));

		String pedidosUrl = linkTo(PedidosController.class).toUri().toString();

		return Link.of(UriTemplate.of(pedidosUrl, PAGE_VARIABLES.concat(filterVariables)), rel);

	}
	
	public Link linkToRestauranteProjection(String rel){
		var restaurantesUrl = linkTo(RestauranteController.class).toUri().toString();
		return Link.of(UriTemplate.of(restaurantesUrl, PROJECTION), rel);
	}	
	public Link linkToCozinha(Long cozinhaId, String rel) {
	    return linkTo(methodOn(CozinhaController.class)
	            .buscar(cozinhaId)).withRel(rel);
	}

	public Link linkToCozinha(Long cozinhaId) {
	    return linkToCozinha(cozinhaId, IanaLinkRelations.SELF.value());
	}
	
	
	public Link linkToRestaurante(Long restauranteId, String rel) {
	    return linkTo(methodOn(RestauranteController.class)
	            .buscar(restauranteId)).withRel(rel);
	}

	public Link linkToRestaurante(Long restauranteId) {
	    return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuario(Long usuarioId, String rel) {
	    return linkTo(methodOn(UsuarioController.class)
	            .buscar(usuarioId)).withRel(rel);
	}

	public Link linkToUsuario(Long usuarioId) {
	    return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuarios(String rel) {
	    return linkTo(UsuarioController.class).withRel(rel);
	}

	public Link linkToUsuarios() {
	    return linkToUsuarios(IanaLinkRelations.SELF.value());
	}

	public Link linkToGruposUsuario(Long usuarioId, String rel) {
	    return linkTo(methodOn(UsuarioGrupoController.class)
	            .listar(usuarioId)).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId) {
	    return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
	    return linkTo(methodOn(RestauranteUsuarioController.class)
	            .listar(restauranteId)).withRel(rel);
	}

	public Link linkToActiveRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel(rel);
	}

	public Link linkToInactiveRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).inativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToOpenRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrir(restauranteId)).withRel(rel);
	}

	public Link linkToCloseRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).fechar(restauranteId)).withRel(rel);
	}
	
	public Link linkToResponsaveisRestaurante(Long restauranteId) {
	    return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
	    return linkTo(methodOn(RestauranteFormasPagamentoController.class)
	            .listar(restauranteId)).withRel(rel);
	}
	
	
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
	    return linkTo(methodOn(FormaPagamentoController.class)
	            .buscar(formaPagamentoId)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
	    return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCidade(Long cidadeId, String rel) {
	    return linkTo(methodOn(CidadeController.class)
	            .buscar(cidadeId)).withRel(rel);
	}

	public Link linkToCidade(Long cidadeId) {
	    return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCidades(String rel) {
	    return linkTo(CidadeController.class).withRel(rel);
	}

	public Link linkToCidades() {
	    return linkToCidades(IanaLinkRelations.SELF.value());
	}

	public Link linkToEstado(Long estadoId, String rel) {
	    return linkTo(methodOn(EstadoController.class)
	            .buscar(estadoId)).withRel(rel);
	}

	public Link linkToEstado(Long estadoId) {
	    return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEstados(String rel) {
	    return linkTo(EstadoController.class).withRel(rel);
	}

	public Link linkToEstados() {
	    return linkToEstados(IanaLinkRelations.SELF.value());
	}

	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
	    return linkTo(methodOn(RestauranteProdutosController.class)
	            .buscar(restauranteId, produtoId))
	            .withRel(rel);
	}

	public Link linkToProduto(Long restauranteId, Long produtoId) {
	    return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCozinhas(String rel) {
	    return linkTo(CozinhaController.class).withRel(rel);
	}

	public Link linkToCozinhas() {
	    return linkToCozinhas(IanaLinkRelations.SELF.value());
	}


	public Link linkToConfirmPedido(String codigoString, String rel) {
		return linkTo(methodOn(PedidoStatusController.class)
				.confirmar(codigoString))
				.withRel(rel);
	}
	
	public Link linkToDeliveryPedido(String codigoString, String rel) {
		return linkTo(methodOn(PedidoStatusController.class)
				.entregar(codigoString))
				.withRel(rel);
	}
	
	public Link linkToCancelPedido(String codigoString, String rel) {
		return linkTo(methodOn(PedidoStatusController.class)
				.cancelar(codigoString))
				.withRel(rel);
	}

	public Link linkToRemoveFormaPagamento(Long restauranteId, Long formaPagamentoId, String rel) {
		return linkTo(methodOn(RestauranteFormasPagamentoController.class)
				.desassociar(restauranteId, formaPagamentoId))
				.withRel(rel);
	}

	
	public Link linkToAddFormaPagamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormasPagamentoController.class)
				.associar(restauranteId, null))
				.withRel(rel);
	}

	public Link linkToRestauranteUsuarioRemover(Long restauranteId, Long id, String rel) {
		return  linkTo(methodOn(RestauranteUsuarioController.class)
				.desassocioarUsuario(restauranteId, id))
				.withRel(rel);
	}
	
	public Link linkToRestauranteUsuarioAdd(Long restauranteId, String rel) {
		return  linkTo(methodOn(RestauranteUsuarioController.class)
				.desassocioarUsuario(restauranteId, null))
				.withRel(rel);
	}
	
}
