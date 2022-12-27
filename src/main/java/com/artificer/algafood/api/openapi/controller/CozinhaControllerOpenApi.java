package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import com.artificer.algafood.api.model.CozinhaModel;
import com.artificer.algafood.api.model.input.CozinhaInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinha")
public interface CozinhaControllerOpenApi {

	PagedModel<CozinhaModel> listar(Pageable pageable);

	CozinhaModel buscar(Long cozinhaId);

	CozinhaModel adicionar(CozinhaInput cozinhaInput);

	CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

	ResponseEntity<Void> remover(Long cozinhaId);
}
