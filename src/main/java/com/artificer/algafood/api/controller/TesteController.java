package com.artificer.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.repository.CozinhaRepository;
import com.artificer.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}

	@GetMapping("restaurantes/por-nome-e-frete")
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("restaurantes/com-frete-gratis")
	public List<Restaurante> find(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}

}
