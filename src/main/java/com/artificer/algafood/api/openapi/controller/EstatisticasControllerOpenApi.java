package com.artificer.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.controller.EstatisticasController.EstatisticaModel;
import com.artificer.algafood.domain.model.statistic.VendasDiaria;
import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatisticas")
public interface EstatisticasControllerOpenApi {

	EstatisticaModel estatisticas();

	List<VendasDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}
