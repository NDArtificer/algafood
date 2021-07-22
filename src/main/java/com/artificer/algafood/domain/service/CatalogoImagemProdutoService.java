package com.artificer.algafood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.model.FotoProduto;
import com.artificer.algafood.domain.repository.ProdutoRepository;

@Service
public class CatalogoImagemProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		Long restauranteId = foto.getProduto().getRestaurante().getId();
		Long produtoId = foto.getProduto().getId();		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);

		if(fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}
		
		return produtoRepository.save(foto);

	}

}
