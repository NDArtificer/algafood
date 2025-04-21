package com.artificer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.FotoProduto;
@Repository
public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);
	void delete(FotoProduto foto);
}