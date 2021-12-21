package com.artificer.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.input.UsuarioInputConverter;
import com.artificer.algafood.api.converter.model.UsuarioModelConverter;
import com.artificer.algafood.api.model.UsuarioModel;
import com.artificer.algafood.api.model.input.SenhaInput;
import com.artificer.algafood.api.model.input.UsuarioComSenhaInput;
import com.artificer.algafood.api.model.input.UsuarioInput;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.repository.UsuarioRepository;
import com.artificer.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService usuarioService;

	@Autowired
	private UsuarioModelConverter modelConverter;

	@Autowired
	private UsuarioInputConverter inputConverter;

	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		return modelConverter.toCollectionModel(usuarioRepository.findAll());
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Readable
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscar(usuarioId);
		return modelConverter.toModel(usuario);
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Editble
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario usuario = inputConverter.toDomainObject(usuarioInput);
		return modelConverter.toModel(usuarioService.salvar(usuario));

	}

	@CheckSecurity.UsuariosGrupoPermissoes.userEditble
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = usuarioService.buscar(usuarioId);
		inputConverter.copyToDomainObject(usuarioInput, usuario);
		return modelConverter.toModel(usuarioService.salvar(usuario));

	}

	@CheckSecurity.UsuariosGrupoPermissoes.passwordEditble
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}

}
