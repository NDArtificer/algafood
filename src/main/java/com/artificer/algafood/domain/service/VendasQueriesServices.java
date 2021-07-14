package com.artificer.algafood.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.model.statistic.VendasDiaria;
import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;

@Service
public interface VendasQueriesServices {
	List<VendasDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
}
