package com.artificer.algafood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.artificer.algafood.core.data.PageableTranslator;
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

	@GetMapping
	private Page<PedidoResumoModel> listar(PedidoFilter filter, @PageableDefault(size = 5) Pageable pageable) {
		pageable = convertPage(pageable);
		
		Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filter), pageable);
		List<PedidoResumoModel> pedidosResumidos = modelResumoConverter.toColletionModel(pedidos.getContent());
		Page<PedidoResumoModel> pedidosResumidosPages = new PageImpl<>(pedidosResumidos, pageable,
				pedidos.getTotalElements());
		return pedidosResumidosPages;
	}

	@GetMapping("/{codigoPedido}")
	private PedidoModel buscar(@PathVariable String codigoPedido) {
		return modelConverter.toModel(cadastroPedido.buscar(codigoPedido));
	}

	@PostMapping
	private PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido pedido = inputConverter.toDomainModel(pedidoInput);

			pedido.setCliente(new Usuario());
			pedido.getCliente().setId(1L);
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
