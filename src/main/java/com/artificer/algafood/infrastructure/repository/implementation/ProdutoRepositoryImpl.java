package com.artificer.algafood.infrastructure.repository.implementation;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.FotoProduto;
import com.artificer.algafood.domain.repository.ProdutoRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

	@Override
	public void delete(FotoProduto foto) {
		manager.remove(foto);
	}

}
