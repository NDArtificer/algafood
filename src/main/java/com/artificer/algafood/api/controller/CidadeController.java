package com.artificer.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.artificer.algafood.api.converter.input.CidadeInputConverter;
import com.artificer.algafood.api.converter.model.CidadeModelConverter;
import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.api.model.input.CidadeInput;
import com.artificer.algafood.domain.exception.EstadoNaoEncontradaException;
import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.repository.CidadeRepository;
import com.artificer.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelConverter cidadeModelConverter;

	@Autowired
	private CidadeInputConverter cidadeInputConverter;

	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelConverter.toColletionModel(cidadeRepository.findAll());
	}

	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		return cidadeModelConverter.toModel(cadastroCidade.buscar(cidadeId));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputConverter.toDomainObject(cidadeInput);
			return cidadeModelConverter.toModel(cadastroCidade.salvar(cidade));

		} catch (EstadoNaoEncontradaException e) {

			throw new NegocioException(e.getMessage(), e);
		}

	}

	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {

		try {
			Cidade cidadeAtual = cadastroCidade.buscar(cidadeId);
			cidadeInputConverter.copyToDomainObject(cidadeInput, cidadeAtual);

			return cidadeModelConverter.toModel(cadastroCidade.salvar(cidadeAtual));

		} catch (EstadoNaoEncontradaException e) {

			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);

	}

}
