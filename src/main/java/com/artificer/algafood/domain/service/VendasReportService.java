package com.artificer.algafood.domain.service;

import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.repository.filter.VendaDiariaFilter;

@Service
public interface VendasReportService {

	byte[] emitirVendaDiarias(VendaDiariaFilter filter, String timeOffSet);

}
