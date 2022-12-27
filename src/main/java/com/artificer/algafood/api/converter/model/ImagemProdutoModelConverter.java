package com.artificer.algafood.api.converter.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.RestauranteProdutoImagemController;
import com.artificer.algafood.api.model.ImagemProdutoModel;
import com.artificer.algafood.domain.model.FotoProduto;

@Component
public class ImagemProdutoModelConverter extends RepresentationModelAssemblerSupport<FotoProduto, ImagemProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	public ImagemProdutoModelConverter() {
		super(RestauranteProdutoImagemController.class, ImagemProdutoModel.class);

	}

	@Override
	public ImagemProdutoModel toModel(FotoProduto foto) {
		return modelMapper.map(foto, ImagemProdutoModel.class);
	}

}
