package com.artificer.algafood.api.converter.input;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.input.RestauranteInput;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputConveter {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}

	public RestauranteInput toInput(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteInput.class);
	}

	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// * Para mitigar a exception:
		// *org.hibernate.HibernateException: identifier of an instance of
		// com.artificer.algafood.domain.model.Cozinha was altered from 1 to 3
		restaurante.setCozinha(new Cozinha());

		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}

		modelMapper.map(restauranteInput, restaurante);
	}

}
