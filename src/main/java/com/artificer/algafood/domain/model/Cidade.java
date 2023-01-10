package com.artificer.algafood.domain.model;

import com.artificer.algafood.core.validation.Groups;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotBlank
	@Column(nullable = false)
	private String nome;

	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
	@ManyToOne
	private Estado estado;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
