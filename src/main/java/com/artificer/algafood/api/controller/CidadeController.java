package com.artificer.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.artificer.algafood.api.converter.input.CidadeInputConverter;
import com.artificer.algafood.api.converter.model.CidadeModelConverter;
import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.api.model.input.CidadeInput;
import com.artificer.algafood.api.utils.ResourceUriHelper;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.exception.EstadoNaoEncontradaException;
import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.repository.CidadeRepository;
import com.artificer.algafood.domain.service.CadastroCidadeService;

@Controller
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

	@CheckSecurity.Cidades.Editble
	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		return cidadeModelConverter.toCollectionModel(cidadeRepository.findAll());
	}

	@CheckSecurity.Cidades.Editble
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		CidadeModel cidadeModel = cidadeModelConverter.toModel(cadastroCidade.buscar(cidadeId));

		return cidadeModel;

	}

	@CheckSecurity.Cidades.Editble
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputConverter.toDomainObject(cidadeInput);
			CidadeModel cidadeModel = cidadeModelConverter.toModel(cadastroCidade.salvar(cidade));
			ResourceUriHelper.addUriResourceHeader(cidadeModel.getId());

			return cidadeModel;

		} catch (EstadoNaoEncontradaException e) {

			throw new NegocioException(e.getMessage(), e);
		}

	}

	@CheckSecurity.Cidades.Editble
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

	@CheckSecurity.Cidades.Editble
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);

	}

}
