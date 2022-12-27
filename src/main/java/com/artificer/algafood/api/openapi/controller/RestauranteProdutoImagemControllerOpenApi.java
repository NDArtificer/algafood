package com.artificer.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.artificer.algafood.api.model.ImagemProdutoModel;
import com.artificer.algafood.api.model.input.ImagemProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produto Imagem")
public interface RestauranteProdutoImagemControllerOpenApi {
	ImagemProdutoModel atualizarImagem(Long restauranteId, Long produtoId, ImagemProdutoInput fotoProdutoInput)
			throws IOException;

	ResponseEntity<Void> excluirImagem(Long restauranteId, Long produtoId);

	ImagemProdutoModel buscarImagem(Long restauranteId, Long produtoId);

	ResponseEntity<?> servirImagem(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

}
