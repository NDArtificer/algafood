package com.artificer.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.artificer.algafood.core.validation.FreteZeroIncluiDescricao;
import com.artificer.algafood.core.validation.Groups;
import com.artificer.algafood.core.validation.TaxaFrete;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;

@FreteZeroIncluiDescricao(valorField = "taxaFrete", descriptionField = "nome", descriptionRequired = "Frete Grátis")
@Getter
@Setter
@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@TaxaFrete
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cozinha cozinha;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> usuarios = new HashSet<>();

	@Embedded
	private Endereco endereco;

	private Boolean ativo = Boolean.TRUE;

	private Boolean aberto = Boolean.FALSE;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	public void ativar() {
		setAtivo(true);
	}

	public void inativar() {
		setAtivo(false);
	}

	public void abrir() {
		setAberto(true);
	}

	public void fechar() {
		setAberto(false);
	}

	public Boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}

	public Boolean removeFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	public Boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	
	public Boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}
	
	public Boolean adicionarUsuario(Usuario usuario) {
		return getUsuarios().add(usuario);
	}

	public Boolean removerUsuario(Usuario usuario) {
		return getUsuarios().remove(usuario);
	}
	
	
	public boolean isAberto() {
	    return this.aberto;
	}

	public boolean isFechado() {
	    return !isAberto();
	}

	public boolean isInativo() {
	    return !isAtivo();
	}

	public boolean isAtivo() {
	    return this.ativo;
	}

	public boolean aberturaPermitida() {
	    return isAtivo() && isFechado();
	}

	public boolean ativacaoPermitida() {
	    return isInativo();
	}

	public boolean inativacaoPermitida() {
	    return isAtivo();
	}

	public boolean fechamentoPermitido() {
	    return isAberto();
	}     
	

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
		Restaurante other = (Restaurante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
