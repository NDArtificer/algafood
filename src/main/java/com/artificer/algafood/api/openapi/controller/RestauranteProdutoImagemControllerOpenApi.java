package com.artificer.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.artificer.algafood.api.model.ImagemProdutoModel;
import com.artificer.algafood.api.model.input.ImagemProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produto Imagem")
public interface RestauranteProdutoImagemControllerOpenApi {

	ImagemProdutoModel atualizarImagem(
			@Parameter(description = "Id do restaurante", example = "1", required = true) Long restauranteId,
			@Parameter(description = "Id do produto", example = "2", required = true) Long produtoId,
			@RequestBody(required = true) ImagemProdutoInput fotoProdutoInput) throws IOException;

	ResponseEntity<Void> excluirImagem(Long restauranteId, Long produtoId);

	@Operation(summary = "Recupera a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ImagemProdutoModel.class)),
					@Content(mediaType = "image.jpeg", schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = "image.png", schema = @Schema(type = "string", format = "binary")) }) })
	ImagemProdutoModel buscarImagem(Long restauranteId, Long produtoId);

	@Operation(hidden = true)
	ResponseEntity<?> servirImagem(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

}
