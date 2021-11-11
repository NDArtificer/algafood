package com.artificer.algafood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.UsuarioModelConverter;
import com.artificer.algafood.api.model.UsuarioModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private UsuarioModelConverter modelConverter;

	@Autowired
	private ApiLinks apiLinks;

	@CheckSecurity.Restaurantes.Readable
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscar(restauranteId);
		CollectionModel<UsuarioModel> usuariosModel = modelConverter.toCollectionModel(restaurante.getUsuarios())
				.removeLinks()
				.add(linkTo(methodOn(RestauranteUsuarioController.class).listar(restauranteId)).withSelfRel());
		usuariosModel.getContent().forEach(usuarioModel -> {
			usuarioModel.add(
					apiLinks.linkToRestauranteUsuarioRemover(restauranteId, usuarioModel.getId(), "Remover Usuario"));
		});

		return usuariosModel.add(apiLinks.linkToRestauranteUsuarioAdd(restauranteId, "Add Usuario"));
	}

	@CheckSecurity.Restaurantes.Editable
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associoarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarUsuario(restauranteId, usuarioId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.Editable
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassocioarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarUsuario(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
}
