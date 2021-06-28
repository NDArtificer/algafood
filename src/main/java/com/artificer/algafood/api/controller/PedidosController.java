package com.artificer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.PedidoModelConverter;
import com.artificer.algafood.api.model.PedidoModel;
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
	private CadastroPedidoService cadastroPedido;
	
	@GetMapping
	private List<PedidoModel> listar(){
		return modelConverter.toColletionModel(pedidoRepository.findAll());
	}

	@GetMapping("/{pedidoId}")
	private PedidoModel buscar(@PathVariable Long pedidoId) {
		return modelConverter.toModel(cadastroPedido.buscar(pedidoId));
	}
	
}
