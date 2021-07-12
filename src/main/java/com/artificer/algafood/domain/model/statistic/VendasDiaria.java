package com.artificer.algafood.domain.model.statistic;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VendasDiaria {

	private Date data;
	private Long totalVendas;
	private BigDecimal totalFaturado;

}
