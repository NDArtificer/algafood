package com.artificer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.GrupoModelConverter;
import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private GrupoModelConverter modelConverter;
	
	@GetMapping
	public List<GrupoModel> listar(@PathVariable Long usuarioId){
		Usuario usuario = cadastroUsuario.buscar(usuarioId);
		return modelConverter.toColletionModel(usuario.getGrupos());
	}

	@PutMapping("{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long usuarioId,@PathVariable Long grupoId ) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);
	} 
	
	
	@DeleteMapping("{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long usuarioId,@PathVariable Long grupoId ) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
	} 
	
	
	
}