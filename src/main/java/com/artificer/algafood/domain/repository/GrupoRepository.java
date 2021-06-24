package com.artificer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {

}
