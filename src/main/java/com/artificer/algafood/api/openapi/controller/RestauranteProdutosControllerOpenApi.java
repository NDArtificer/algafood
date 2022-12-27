package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.artificer.algafood.api.model.ProdutoModel;
import com.artificer.algafood.api.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produto Restaurante")
public interface RestauranteProdutosControllerOpenApi {

	CollectionModel<ProdutoModel> listar(Long restauranteId, Boolean incluirInativos);

	ProdutoModel buscar(Long restauranteId, Long produtoId);

	ProdutoModel adicionar(Long restauranteId, ProdutoInput produtoInput);

	ProdutoModel atualizar(Long restauranteId, Long produtoId, ProdutoInput produtoInput);

}
