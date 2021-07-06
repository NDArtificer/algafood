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

import com.artificer.algafood.api.converter.input.ProdutoInputConverter;
import com.artificer.algafood.api.converter.model.ProdutoModelConverter;
import com.artificer.algafood.api.model.ProdutoModel;
import com.artificer.algafood.api.model.input.ProdutoInput;
import com.artificer.algafood.domain.model.Produto;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.repository.ProdutoRepository;
import com.artificer.algafood.domain.service.CadastroProdutoService;
import com.artificer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private ProdutoModelConverter modelConverter;

	@Autowired
	private ProdutoInputConverter inputConverter;

	@GetMapping
	public List<ProdutoModel> Listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscar(restauranteId);
		return modelConverter.toColletionModel(produtoRepository.findByRestaurante(restaurante));

	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		return modelConverter.toModel(cadastroProduto.buscarOuFalhar(restauranteId, produtoId));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = cadastroRestaurante.buscar(restauranteId);

		Produto produto = inputConverter.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);

		produto = cadastroProduto.salvar(produto);

		return modelConverter.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		inputConverter.copyToDomainObject(produtoInput, produtoAtual);

		produtoAtual = cadastroProduto.salvar(produtoAtual);

		return modelConverter.toModel(produtoAtual);
	}
	
	
	@PutMapping("/{produtoId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		cadastroProduto.ativarProduto(restauranteId, produtoId);
	}

	@DeleteMapping("/{produtoId}/inativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		cadastroProduto.desativarProduto(restauranteId, produtoId);
	}


}