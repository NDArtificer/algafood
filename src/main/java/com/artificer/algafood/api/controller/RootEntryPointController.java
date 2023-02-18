package com.artificer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.utils.ApiLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private ApiLinks apiLinks;

	@GetMapping(value = "/")	
	public RootEntryPointModel root() {
		var rootEntryPoint = new RootEntryPointModel();

		rootEntryPoint.add(apiLinks.linkToCozinhas("Cozinhas"));
		rootEntryPoint.add(apiLinks.linkToPedidos("Pedidos"));
		rootEntryPoint.add(apiLinks.linkToRestaurante("Restaurantes"));
		rootEntryPoint.add(apiLinks.linkToEstados("Estados"));
		rootEntryPoint.add(apiLinks.linkToCidades("Cidades"));
		rootEntryPoint.add(apiLinks.linkToGrupo("Grupos"));
		rootEntryPoint.add(apiLinks.linkToPermissoes("Permissoes"));
		rootEntryPoint.add(apiLinks.linkToFormaPagamento("Forma Pagamento"));
		rootEntryPoint.add(apiLinks.linkToEstatisticas("Estatisticas"));

		return rootEntryPoint;
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

	}

}
