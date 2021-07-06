package com.artificer.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.repository.PedidoRepository;
import com.artificer.algafood.domain.service.CadastroPedidoService;

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
	private List<PedidoResumoModel> listar() {
		return modelResumoConverter.toColletionModel(pedidoRepository.findAll());
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

}
