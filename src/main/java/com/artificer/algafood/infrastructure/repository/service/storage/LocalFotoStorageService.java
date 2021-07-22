package com.artificer.algafood.infrastructure.repository.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bouncycastle.util.StoreException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.artificer.algafood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Value("${algafood.storage.local.diretorio-fotos}")
	private Path diretorioFotos;

	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
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
	public InputStream recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			return Files.newInputStream(arquivoPath);
		} catch (IOException e) {
			throw new StoreException("Não foi possível excluir o arquivo!", e);
		}
	}

}
