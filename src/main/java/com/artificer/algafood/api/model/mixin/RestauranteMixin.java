package com.artificer.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.Endereco;
import com.artificer.algafood.domain.model.FormaPagamento;
import com.artificer.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class RestauranteMixin {
	
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;

	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private OffsetDateTime dataCadastro;

	@JsonIgnore
	private OffsetDateTime dataAtualizacao;

	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();


}
