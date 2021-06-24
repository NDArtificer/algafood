package com.artificer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

}
