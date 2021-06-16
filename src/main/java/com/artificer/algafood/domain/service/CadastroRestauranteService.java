package com.artificer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = buscar(restauranteId);
		restauranteAtual.ativar();
	}

	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = buscar(restauranteId);
		restauranteAtual.inativar();
	}

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cidade cidade = cadastroCidade.buscar(cidadeId);
		Cozinha cozinha = cadastroCozinha.buscar(cozinhaId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}

}
