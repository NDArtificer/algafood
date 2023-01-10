package com.artificer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.input.CozinhaInputConverter;
import com.artificer.algafood.api.converter.model.CozinhaModelConverter;
import com.artificer.algafood.api.model.CozinhaModel;
import com.artificer.algafood.api.model.input.CozinhaInput;
import com.artificer.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.repository.CozinhaRepository;
import com.artificer.algafood.domain.service.CadastroCozinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastoCozinha;

	@Autowired
	private CozinhaModelConverter cozinhaModelConverter;

	@Autowired
	private CozinhaInputConverter cozinhaInputConverter;

	@Autowired
	private PagedResourcesAssembler<Cozinha> pageAssembler;

	@CheckSecurity.Cozinhas.Readable
	@GetMapping
	public PagedModel<CozinhaModel> listar(Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

		PagedModel<CozinhaModel> cozinhasPaged = pageAssembler.toModel(cozinhasPage, cozinhaModelConverter);

		return cozinhasPaged;
	}

	@CheckSecurity.Cozinhas.Readable
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return cozinhaModelConverter.toModel(cadastoCozinha.buscar(cozinhaId));

	}

	@CheckSecurity.Cozinhas.Editable
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputConverter.toDomainModel(cozinhaInput);
		return cozinhaModelConverter.toModel(cadastoCozinha.salvar(cozinha));
	}

	@CheckSecurity.Cozinhas.Editable
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {

		Cozinha cozinhaAtual = cadastoCozinha.buscar(cozinhaId);
		cozinhaInputConverter.copyToDomainObject(cozinhaInput, cozinhaAtual);

		return cozinhaModelConverter.toModel(cadastoCozinha.salvar(cozinhaAtual));

	}

	@CheckSecurity.Cozinhas.Editable
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
		cadastoCozinha.excluir(cozinhaId);
		return ResponseEntity.noContent().build();
	}
}
