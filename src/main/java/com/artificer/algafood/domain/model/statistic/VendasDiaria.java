package com.artificer.algafood.domain.model.statistic;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendasDiaria {

	private Date data;
	private Long totalVendas;
	private BigDecimal totalFaturado;
	
	public VendasDiaria(java.sql.Date data, Long totalVendas, BigDecimal totalFaturado) {
		this.data = new Date(data.getTime());
		this.totalVendas = totalVendas;
		this.totalFaturado = totalFaturado;
	}

	
	
}
