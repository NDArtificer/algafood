package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.ProdutoInput;
import com.artificer.algafood.domain.model.Produto;

@Component
public class ProdutoInputConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Produto toDomainObject(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}

	public ProdutoInput toInput(Produto produto) {
		return modelMapper.map(produto, ProdutoInput.class);
	}

	public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
	
}
