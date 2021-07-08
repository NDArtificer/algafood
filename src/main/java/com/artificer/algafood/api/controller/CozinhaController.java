package com.artificer.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import com.artificer.algafood.api.converter.input.CozinhaInputConverter;
import com.artificer.algafood.api.converter.model.CozinhaModelConverter;
import com.artificer.algafood.api.model.CozinhaModel;
import com.artificer.algafood.api.model.input.CozinhaInput;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.repository.CozinhaRepository;
import com.artificer.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastoCozinha;

	@Autowired
	private CozinhaModelConverter cozinhaModelConverter;

	@Autowired
	private CozinhaInputConverter cozinhaInputConverter;

	@GetMapping
	public Page<CozinhaModel> listar(Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

		List<CozinhaModel> cozinhasModel = cozinhaModelConverter.toColletionModel(cozinhasPage.getContent());

		Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable, cozinhasPage.getTotalElements());
		
		return cozinhasModelPage;
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return cozinhaModelConverter.toModel(cadastoCozinha.buscar(cozinhaId));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputConverter.toDomainModel(cozinhaInput);
		return cozinhaModelConverter.toModel(cadastoCozinha.salvar(cozinha));
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {

		Cozinha cozinhaAtual = cadastoCozinha.buscar(cozinhaId);
		cozinhaInputConverter.copyToDomainObject(cozinhaInput, cozinhaAtual);

		return cozinhaModelConverter.toModel(cadastoCozinha.salvar(cozinhaAtual));

	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastoCozinha.excluir(cozinhaId);
	}
}
