package com.artificer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.FormaPagamentoModelConverter;
import com.artificer.algafood.api.model.FormaPagamentoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private FormaPagamentoModelConverter modelConverter;

	@Autowired
	private ApiLinks apiLinks;

	@CheckSecurity.Restaurantes.Readable
	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscar(restauranteId);
		CollectionModel<FormaPagamentoModel> formasPagamento = modelConverter
				.toCollectionModel(restaurante.getFormasPagamento());

		formasPagamento.getContent().forEach(formaPagamento -> {
			Long formaPagamentoId = formaPagamento.getId();
			formaPagamento.add(
					apiLinks.linkToRemoveFormaPagamento(restauranteId, formaPagamentoId, "Forma Pagamento Remover"));
		});

		return formasPagamento.add(apiLinks.linkToAddFormaPagamento(restauranteId, "Forma Pagamento Add"));
	}

	@CheckSecurity.Restaurantes.Editable
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.accepted().build();
	}

	@CheckSecurity.Restaurantes.Editable
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

}
