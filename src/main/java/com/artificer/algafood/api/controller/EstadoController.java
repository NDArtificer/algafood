package com.artificer.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.input.EstadoInputConverter;
import com.artificer.algafood.api.converter.model.EstadoModelConverter;
import com.artificer.algafood.api.model.EstadoModel;
import com.artificer.algafood.api.model.input.EstadoInput;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Estado;
import com.artificer.algafood.domain.repository.EstadoRepository;
import com.artificer.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoModelConverter estadoModelConverter;

	@Autowired
	private EstadoInputConverter estadoInputConverter;

	@GetMapping
	public CollectionModel<EstadoModel> listar() {

		return estadoModelConverter.toCollectionModel(estadoRepository.findAll());
	}

	@CheckSecurity.Estados.Readable
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {

		return estadoModelConverter.toModel(cadastroEstado.buscar(estadoId));

	}

	@CheckSecurity.Estados.Editble
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {

		Estado estado = estadoInputConverter.toDomainModel(estadoInput);
		return estadoModelConverter.toModel(cadastroEstado.salvar(estado));

	}

	@CheckSecurity.Estados.Editble
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {

		Estado estadoAtual = cadastroEstado.buscar(estadoId);
		estadoInputConverter.copyToDomainObject(estadoInput, estadoAtual);

		return estadoModelConverter.toModel(cadastroEstado.salvar(estadoAtual));

	}

	@CheckSecurity.Estados.Editble
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {

		cadastroEstado.excluir(estadoId);

	}

}
