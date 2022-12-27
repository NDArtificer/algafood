package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurante Forma Pagamento")
public interface RestauranteFormasPagamentoControllerOpenApi {
	CollectionModel<FormaPagamentoModel> listar(Long restauranteId);

	ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);

	ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);

}
