package com.artificer.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	InputStream recuperar(String nomeArquivo);
	
	void armazenar(NovaFoto novaFoto);

	void remover(String nomeArquivo);

	default void substituir(String arquivoExistente, NovaFoto novaFoto) {
		this.armazenar(novaFoto);

		if (arquivoExistente != null) {
			this.remover(arquivoExistente);
		}
	}

	default String generateFileName(String originalName) {
		return UUID.randomUUID().toString() + "_" + originalName;
	}

	@Builder
	@Getter
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}

}
