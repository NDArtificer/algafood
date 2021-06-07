package com.artificer.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.artificer.algafood.domain.model.Grupo;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class UsuarioMixin {

	@JsonIgnore
	private List<Grupo> grupos = new ArrayList<>();
	
}
