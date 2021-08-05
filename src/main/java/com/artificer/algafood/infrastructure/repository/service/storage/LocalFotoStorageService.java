package com.artificer.algafood.infrastructure.repository.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bouncycastle.util.StoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.artificer.algafood.core.storage.StorageProperties;
import com.artificer.algafood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;

	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal()
				.getDirectoryPhotos()
				.resolve(Path.of(nomeArquivo));
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
		Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

		try {
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (IOException e) {
			throw new StoreException("Não foi possível armazenar o arquivo!", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		Path arquivoPath = getArquivoPath(nomeArquivo);
		try {
			Files.deleteIfExists(arquivoPath);
		} catch (IOException e) {
			throw new StoreException("Não foi possível excluir o arquivo!", e);
		}

	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(Files.newInputStream(arquivoPath))
					.build();
			
			return fotoRecuperada;
		} catch (IOException e) {
			throw new StoreException("Não foi possível excluir o arquivo!", e);
		}
	}

}
