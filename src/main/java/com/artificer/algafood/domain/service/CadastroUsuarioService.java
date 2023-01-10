package com.artificer.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.exception.UsuarioNaoEncontradaException;
import com.artificer.algafood.domain.model.Grupo;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradaException(usuarioId));
	}

	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail informado! %s", usuario.getEmail()));
		}

		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}

		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscar(usuarioId);

		if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(passwordEncoder.encode(novaSenha));
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscar(usuarioId);
		Grupo grupo = cadastroGrupo.buscar(grupoId);
		usuario.adicionarGrupo(grupo);
	}

	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscar(usuarioId);
		Grupo grupo = cadastroGrupo.buscar(grupoId);
		usuario.removerGrupo(grupo);
	}

}
