package com.artificer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastoCozinhaService cadastroCozinha;

	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cadastroCozinha.buscar(cozinhaId);
		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

}