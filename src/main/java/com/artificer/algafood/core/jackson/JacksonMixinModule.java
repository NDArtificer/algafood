package com.artificer.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.artificer.algafood.api.model.mixin.CidadeMixin;
import com.artificer.algafood.api.model.mixin.CozinhaMixin;
import com.artificer.algafood.api.model.mixin.GrupoMixin;
import com.artificer.algafood.api.model.mixin.PedidoMixin;
import com.artificer.algafood.api.model.mixin.UsuarioMixin;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.model.Cozinha;
import com.artificer.algafood.domain.model.Grupo;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.model.Usuario;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Pedido.class, PedidoMixin.class);
		setMixInAnnotation(Grupo.class, GrupoMixin.class);
		setMixInAnnotation(Usuario.class, UsuarioMixin.class);
	}
}
