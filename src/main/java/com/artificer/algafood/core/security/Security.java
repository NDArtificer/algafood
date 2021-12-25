package com.artificer.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.artificer.algafood.domain.repository.PedidoRepository;
import com.artificer.algafood.domain.repository.RestauranteRepository;

@Component
public class Security {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Long getUsurioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("usuario_id");
	}

	public boolean manageRestaurante(Long restauranteId) {

		if (restauranteId == null) {
			return false;
		}
		return restauranteRepository.existsUsuario(restauranteId, getUsurioId());
	}

	public boolean usuarioAuthenticatedEquals(Long usuarioId) {
		return getUsurioId() != null && usuarioId != null && getUsurioId().equals(usuarioId);
	}

	public boolean manageRestaurantePedido(String codigoPedido) {
		return pedidoRepository.isPedidoManegeableBy(codigoPedido, getUsurioId());
	}

	public boolean isPedidoManegeable(String codigoPedido) {
		return hasAuthority("SCOPE_WRITE")
				&& (hasAuthority("GERENCIAR_PEDIDOS") || manageRestaurantePedido(codigoPedido));
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority().equals(authorityName));
	}
}
