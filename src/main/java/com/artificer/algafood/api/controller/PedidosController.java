package com.artificer.algafood.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.input.PedidoInputConverter;
import com.artificer.algafood.api.converter.model.PedidoModelConverter;
import com.artificer.algafood.api.converter.model.PedidoResumoModelConverter;
import com.artificer.algafood.api.model.PedidoModel;
import com.artificer.algafood.api.model.PedidoResumoModel;
import com.artificer.algafood.api.model.input.PedidoInput;
import com.artificer.algafood.core.data.PageWrapper;
import com.artificer.algafood.core.data.PageableTranslator;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.core.security.Security;
import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.repository.PedidoRepository;
import com.artificer.algafood.domain.repository.filter.PedidoFilter;
import com.artificer.algafood.domain.service.CadastroPedidoService;
import com.artificer.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoModelConverter modelConverter;

	@Autowired
	private PedidoResumoModelConverter modelResumoConverter;

	@Autowired
	private PedidoInputConverter inputConverter;

	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pageAssembler;
	
	@Autowired
	private Security security;

	@CheckSecurity.Pedidos.Searchble
	@GetMapping
	public PagedModel<PedidoResumoModel> listar(PedidoFilter filter, @PageableDefault(size = 5) Pageable pageable) {
		
		Pageable pageableTransalated = convertPage(pageable);
		
		Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filter), pageableTransalated);
		PagedModel<PedidoResumoModel> pedidosResumidosPages = pageAssembler.toModel(pedidos, modelResumoConverter) ;
		
		pedidos = new PageWrapper<>(pedidos, pageable);
		
		return pedidosResumidosPages;
	}

	
	@CheckSecurity.Pedidos.Consultable
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		return modelConverter.toModel(cadastroPedido.buscar(codigoPedido));
	}

	@CheckSecurity.Pedidos.Creatable
	@PostMapping
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido pedido = inputConverter.toDomainModel(pedidoInput);

			pedido.setCliente(new Usuario());
			pedido.getCliente().setId(security.getUsurioId());
			return modelConverter.toModel(cadastroPedido.emitir(pedido));

		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable convertPage(Pageable apiPage) {
		var mapeamento = Map.of(
				"codigo","codigo",
				"restaurante.nome", "restaurante.nome",
				"nomeCliente","cliente.nome",
				"cliente.nome","cliente.nome",
				"valorTotal", "valorTotal"
				);
		
		return PageableTranslator.translate(apiPage, mapeamento);
	}
	
}
