package com.artificer.algafood.api.openapi.controller;


import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.api.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	CollectionModel<CidadeModel> listar();

	CidadeModel buscar(Long cidadeId);

	CidadeModel adicionar(CidadeInput cidadeInput);

	CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);

	ResponseEntity<Void> remover(Long cidadeId);
}