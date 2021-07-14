package com.artificer.algafood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.FormaPagamento;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

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
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}

	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
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

	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscar(formaPagamentoId);

		restaurante.adicionarFormaPagamento(formaPagamento);

	}

	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscar(formaPagamentoId);

		restaurante.removeFormaPagamento(formaPagamento);

	}

	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = buscar(restauranteId);
		restaurante.abrir();
	}

	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = buscar(restauranteId);
		restaurante.fechar();
	}

	@Transactional
	public void associarUsuario(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscar(restauranteId);
		Usuario usuario = cadastroUsuario.buscar(usuarioId);

		restaurante.adicionarUsuario(usuario);
	}

	@Transactional
	public void desassociarUsuario(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscar(restauranteId);
		Usuario usuario = cadastroUsuario.buscar(usuarioId);

		restaurante.removerUsuario(usuario);

	}

}
