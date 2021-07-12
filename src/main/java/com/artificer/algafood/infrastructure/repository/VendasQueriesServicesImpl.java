package com.artificer.algafood.infrastructure.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.enums.StatusPedido;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.model.statistic.VendasDiaria;
import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;
import com.artificer.algafood.domain.service.VendasQueriesServices;

@Repository
public class VendasQueriesServicesImpl implements VendasQueriesServices {

	@Autowired
	private EntityManager entity;

	@Override
	public List<VendasDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		var predicates = new ArrayList<Predicate>();

		var builder = entity.getCriteriaBuilder();
		var query = builder.createQuery(VendasDiaria.class);
		var root = query.from(Pedido.class);

		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), (filtro.getRestauranteId())));
		}
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}
		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
		}
		var functionDateDataCriacao = builder.function("date", Date.class, root.get("dataCriacao"));

		var selector = builder.construct(VendasDiaria.class, functionDateDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));

		query.where(predicates.toArray(new Predicate[0]));
		query.select(selector);
		query.groupBy(functionDateDataCriacao);

		return entity.createQuery(query).getResultList();
	}

}
