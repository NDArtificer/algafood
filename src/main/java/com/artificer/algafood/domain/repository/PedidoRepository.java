package com.artificer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
