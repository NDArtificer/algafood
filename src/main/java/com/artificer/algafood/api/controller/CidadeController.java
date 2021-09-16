package com.artificer.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
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
import com.artificer.algafood.api.utils.ResourceUriHelper;
import com.artificer.algafood.domain.exception.EstadoNaoEncontradaException;
import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.repository.CidadeRepository;
import com.artificer.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cidades")
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

	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelConverter.toColletionModel(cidadeRepository.findAll());
	}

	@ApiOperation("Busca uma cidade por Id")
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(
			@ApiParam(value = "Id de uma cidade", example = "1") 
			@PathVariable Long cidadeId) {
		return cidadeModelConverter.toModel(cadastroCidade.buscar(cidadeId));

	}

	@ApiOperation("Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(
			@ApiParam(name ="Corpo", value = "Representação de uma nova cidade")
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputConverter.toDomainObject(cidadeInput);
			CidadeModel cidadeModel= cidadeModelConverter.toModel(cadastroCidade.salvar(cidade));
			ResourceUriHelper.addUriResourceHeader(cidadeModel.getId());
			
			return cidadeModel;
			
		} catch (EstadoNaoEncontradaException e) {

			throw new NegocioException(e.getMessage(), e);
		}

	}

	@ApiOperation("Atualiza uma cidade por Id")
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(
			@ApiParam(name ="Corpo", value = "Atualização de uma cidade")
			@PathVariable Long cidadeId, 
			@ApiParam(value = "Id de uma cidade", example = "1")  
			@RequestBody @Valid CidadeInput cidadeInput) {

		try {
			Cidade cidadeAtual = cadastroCidade.buscar(cidadeId);
			cidadeInputConverter.copyToDomainObject(cidadeInput, cidadeAtual);

			return cidadeModelConverter.toModel(cadastroCidade.salvar(cidadeAtual));

		} catch (EstadoNaoEncontradaException e) {

			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Remove uma cidade por Id")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "Id de uma cidade", example = "1") @PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);

	}

}
