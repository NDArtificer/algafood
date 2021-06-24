package com.artificer.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	
	List<Cozinha> findByNomeContaining(String nome);

}
