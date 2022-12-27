package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.RestauranteProdutosController;
import com.artificer.algafood.api.model.ProdutoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.model.Produto;

@Component
public class ProdutoModelConverter extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinks apiLinks;

	public ProdutoModelConverter() {
		super(RestauranteProdutosController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		var produtoModel = modelMapper.map(produto, ProdutoModel.class);

		produtoModel.add(apiLinks.linkToProduto(produto.getRestaurante().getId(), produto.getId()));
		produtoModel.add(apiLinks.linkToFotoProduto(produto.getRestaurante().getId(), produto.getId(), "Imagem"));
		return produtoModel;
	}

	@Override
	public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {
		return super.toCollectionModel(entities);
	}

}
