package com.artificer.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.artificer.algafood.domain.model.FormaPagamento;
import com.artificer.algafood.domain.repository.FormaPagamentoRepository;
import com.artificer.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {

	@Autowired
	private CadastroFormaPagamentoService cadastroPagamento;

	@Autowired
	private FormaPagamentoRepository formaPagamentoRespository;

	@Autowired
	private FormaPagamentoModelConverter formaPagamentoModelConverter;

	@Autowired
	private FormaPagamentoInputConverter formaPagamentoInputConverter;

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar() {
		return formaPagamentoModelConverter.toCollectionModel(formaPagamentoRespository.findAll());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
		return formaPagamentoModelConverter.toModel(cadastroPagamento.buscar(formaPagamentoId));
	}

	@PreAuthorize("hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputConverter.toDomainModel(formaPagamentoInput);

		return formaPagamentoModelConverter.toModel(cadastroPagamento.salvar(formaPagamento));

	}

	@PreAuthorize("hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = cadastroPagamento.buscar(formaPagamentoId);
		formaPagamentoInputConverter.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		return formaPagamentoModelConverter.toModel(cadastroPagamento.salvar(formaPagamentoAtual));

	}

	@PreAuthorize("hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long formaPagamentoId) {
		cadastroPagamento.excluir(formaPagamentoId);
	}
}
