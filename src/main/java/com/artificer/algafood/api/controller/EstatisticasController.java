package com.artificer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.statistic.VendasDiaria;
import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;
import com.artificer.algafood.domain.service.VendasQueriesServices;
import com.artificer.algafood.domain.service.VendasReportService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

	@Autowired
	private VendasQueriesServices vendaService;

	@Autowired
	private VendasReportService reportservice;

	@Autowired
	private ApiLinks apiLinks;

	@CheckSecurity.Estatisticas.Readable
	@GetMapping
	public EstatisticaModel estatistica() {
		var estatisticaModel = new EstatisticaModel();

		estatisticaModel.add(apiLinks.linkToVendasDiarias("Vendas-Diarias"));

		return estatisticaModel;
	}

	@CheckSecurity.Estatisticas.Readable
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendasDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
		return vendaService.consultarVendasDiarias(filter, timeOffSet);
	}

	@CheckSecurity.Estatisticas.Readable
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filter,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {

		byte[] bytesPdf = reportservice.emitirVendaDiarias(filter, timeOffSet);

		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
	}

	private static class EstatisticaModel extends RepresentationModel<EstatisticaModel> {

	}

}
