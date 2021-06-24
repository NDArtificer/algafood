package com.artificer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
