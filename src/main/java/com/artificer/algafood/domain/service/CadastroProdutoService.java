package com.artificer.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.artificer.algafood.domain.model.Produto;
import com.artificer.algafood.domain.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto produto) {
		produtoRepository.detach(produto);

		Optional<Produto> produtoExistente = produtoRepository.findByNome(produto.getNome());

		if (produtoExistente.isPresent() && !produtoExistente.get().equals(produto)
				&& produtoExistente.get().getRestaurante().getId().equals(produto.getRestaurante().getId())) {
			throw new NegocioException(String.format("O produto com este nome %s já existe", produto.getNome()));
		}

		return produtoRepository.save(produto);
	}

	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}
	
	@Transactional
	public void ativarProduto(Long restauranteId, Long produtoId) {
		Produto produto = buscarOuFalhar(restauranteId, produtoId);
		produto.ativar();
	}
	
	@Transactional
	public void desativarProduto(Long restauranteId, Long produtoId) {
		Produto produto = buscarOuFalhar(restauranteId, produtoId);
		produto.inativar();
	}

}
