package com.artificer.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinhas {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Editable {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {
		}

	}

	public @interface Restaurantes {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Editable {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') " + "and (hasAuthority('EDITAR_RESTAURANTES') "
				+ "or @security.manageRestaurante(#restauranteId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface manageable {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {
		}

	}

	public @interface Pedidos {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') "
				+ "or @security.usuarioAuthenticatedEquals(returnObject.cliente.id) "
				+ "or @security.manageRestaurante(returnObject.restaurante.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Consultable {

		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@security.usuarioAuthenticatedEquals(#filter.clienteId) or"
				+ "@security.manageRestaurante(#filter.restauranteId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Searchble {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('GERENCIAR_PEDIDOS') or "
				+ "@security.gerenciaRestauranteDoPedido(#codigoPedido))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Manageable {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Creatable {
		}

	}

	public @interface FormasPagamento {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {

		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_FORMAS_PAGAMENTO'))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Editble {

		}

	}

	public @interface Cidades {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {

		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_CIDADES'))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Editble {

		}

	}

	public @interface Estados {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {

		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_ESTADOS'))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Editble {

		}

	}

	public @interface UsuariosGrupoPermissoes {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @security.usuarioAuthenticatedEquals(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface passwordEditble {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS')"
				+ "or @security.getUsurioId() == #usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface userEditble {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS'))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Editble {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_USUARIOS'))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {
		}
	}

	public @interface Estatisticas {

		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('GERAR_RELATORIOS'))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Readable {
		}
	}

}
