package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.FormaPagamentoModel;
import com.artificer.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Forma Pagamento")
public interface FormaPagamentoControllerOpenApi {
	CollectionModel<FormaPagamentoModel> listar();

	FormaPagamentoModel buscar(Long formaPagamentoId);

	FormaPagamentoModel adicionar(FormaPagamentoInput formaPagamentoInput);

	FormaPagamentoModel atualizar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput);

	ResponseEntity<Void> remover(Long formaPagamentoId);

}
