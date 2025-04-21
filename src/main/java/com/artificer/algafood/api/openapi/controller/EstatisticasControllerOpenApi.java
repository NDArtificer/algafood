package com.artificer.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.controller.EstatisticasController.EstatisticaModel;
import com.artificer.algafood.domain.model.statistic.VendasDiaria;
import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatisticas")
public interface EstatisticasControllerOpenApi {

	@Operation(summary = "Filtro de Estatisticas")
	EstatisticaModel estatisticas();
	
	
	@Operation(summary = "Filtro de Estatisticas", responses = { @ApiResponse(responseCode = "200")})
	@Parameter(in = ParameterIn.QUERY, name = "restauranteId", description = "Id do Restaurante", schema = @Schema(type = "integer", defaultValue = "10"))
	@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio", description = "Data de inicio das vendas", schema = @Schema(type = "offSetDateTime", defaultValue = "2023-03-19T11:46:14.250Z"))
	@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim", description = "Data fim das vendas",schema = @Schema(type = "offSetDateTime", defaultValue = "2023-03-19T11:46:14.250Z"))
	List<VendasDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}
