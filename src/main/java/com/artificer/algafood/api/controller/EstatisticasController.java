package com.artificer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.domain.model.statistic.VendasDiaria;
import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;
import com.artificer.algafood.domain.service.VendasQueriesServices;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

	@Autowired
	private VendasQueriesServices vendaService;

	@GetMapping("/vendas-diarias")
	public List<VendasDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
		return vendaService.consultarVendasDiarias(filter);
	}

}
