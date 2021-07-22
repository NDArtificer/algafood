package com.artificer.algafood.infrastructure.repository.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;
import com.artificer.algafood.domain.service.VendasQueriesServices;
import com.artificer.algafood.domain.service.VendasReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendasReportService implements VendasReportService {

	@Autowired
	private VendasQueriesServices vendaService;

	@Override
	public byte[] emitirVendaDiarias(VendaDiariaFilter vendas, String timeOffSet) {

		var inputStream = this.getClass().getResourceAsStream("/reports/vendas-diarias.jasper");

		var paramtros = new HashMap<String, Object>();
		paramtros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		var vendasDiarias = vendaService.consultarVendasDiarias(vendas, timeOffSet);

		var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

		try {
			var jasperPrint = JasperFillManager.fillReport(inputStream, paramtros, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possível gerar o relatório de vendas diárias!", e);
		}
	}

}
