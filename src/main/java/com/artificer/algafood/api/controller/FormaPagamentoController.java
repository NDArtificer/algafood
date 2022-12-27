package com.artificer.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.input.FormaPagamentoInputConverter;
import com.artificer.algafood.api.converter.model.FormaPagamentoModelConverter;
import com.artificer.algafood.api.model.FormaPagamentoModel;
import com.artificer.algafood.api.model.input.FormaPagamentoInput;
import com.artificer.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.FormaPagamento;
import com.artificer.algafood.domain.repository.FormaPagamentoRepository;
import com.artificer.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formasPagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroFormaPagamentoService cadastroPagamento;

	@Autowired
	private FormaPagamentoRepository formaPagamentoRespository;

	@Autowired
	private FormaPagamentoModelConverter formaPagamentoModelConverter;

	@Autowired
	private FormaPagamentoInputConverter formaPagamentoInputConverter;

	@CheckSecurity.FormasPagamento.Readable
	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar() {
		return formaPagamentoModelConverter.toCollectionModel(formaPagamentoRespository.findAll());
	}

	@CheckSecurity.FormasPagamento.Readable
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
		return formaPagamentoModelConverter.toModel(cadastroPagamento.buscar(formaPagamentoId));
	}

	@CheckSecurity.FormasPagamento.Editble
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {

		FormaPagamento formaPagamento = formaPagamentoInputConverter.toDomainModel(formaPagamentoInput);

		return formaPagamentoModelConverter.toModel(cadastroPagamento.salvar(formaPagamento));

	}

	@CheckSecurity.FormasPagamento.Editble
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = cadastroPagamento.buscar(formaPagamentoId);
		formaPagamentoInputConverter.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		return formaPagamentoModelConverter.toModel(cadastroPagamento.salvar(formaPagamentoAtual));

	}

	@CheckSecurity.FormasPagamento.Editble
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long formaPagamentoId) {
		cadastroPagamento.excluir(formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

}
