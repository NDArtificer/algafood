package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.UsuarioModel;
import com.artificer.algafood.api.model.input.SenhaInput;
import com.artificer.algafood.api.model.input.UsuarioComSenhaInput;
import com.artificer.algafood.api.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuario")
public interface UsuarioControllerOpenApi {

	CollectionModel<UsuarioModel> listar();

	UsuarioModel buscar(Long usuarioId);

	UsuarioModel adicionar(UsuarioComSenhaInput usuarioInput);

	UsuarioModel atualizar(Long usuarioId, UsuarioInput usuarioInput);

	ResponseEntity<Void> alterarSenha(Long usuarioId, SenhaInput senha);

}
