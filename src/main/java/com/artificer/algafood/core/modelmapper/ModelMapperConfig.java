package com.artificer.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.artificer.algafood.api.model.EnderecoModel;
import com.artificer.algafood.api.model.input.ItemPedidoInput;
import com.artificer.algafood.domain.model.Endereco;
import com.artificer.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		var enderecoToEnderecoModel = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

		enderecoToEnderecoModel.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoDest, value) -> enderecoDest.getCidade().setEstado(value));
		return modelMapper;
	}

}
