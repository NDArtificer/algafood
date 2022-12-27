package com.artificer.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.RestauranteModel;
import com.artificer.algafood.api.model.RestauranteResumoModel;
import com.artificer.algafood.api.model.RestauranteSummaryModel;
import com.artificer.algafood.api.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurante")
public interface RestauranteControllerOpenApi {

	CollectionModel<RestauranteSummaryModel> listar();

	CollectionModel<RestauranteResumoModel> listarNome();

	RestauranteModel buscar(Long restauranteId);

	RestauranteModel adicionar(RestauranteInput restauranteInput);

	RestauranteModel atualizar(Long restauranteId, RestauranteInput restauranteInput);

	ResponseEntity<Void> ativar(Long restauranteId);

	ResponseEntity<Void> inativar(Long restauranteId);

	ResponseEntity<Void> ativarRestaurantes(List<Long> restauranteIds);

	ResponseEntity<Void> inativarRestaurantes(List<Long> restauranteIds);

	ResponseEntity<Void> abrir(Long restauranteId);

	ResponseEntity<Void> fechar(Long restauranteId);
}
