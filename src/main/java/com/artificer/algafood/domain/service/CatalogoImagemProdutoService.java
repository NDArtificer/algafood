package com.artificer.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.FotoNaoEncontradaException;
import com.artificer.algafood.domain.model.FotoProduto;
import com.artificer.algafood.domain.repository.ProdutoRepository;
import com.artificer.algafood.domain.service.FotoStorageService.NovaFoto;

import jakarta.transaction.Transactional;

@Service
public class CatalogoImagemProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FotoStorageService storageService;

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getProduto().getRestaurante().getId();
		Long produtoId = foto.getProduto().getId();
		String novoNomeArquivo = storageService.generateFileName(foto.getNomeArquivo());
		String arquivoExistente = null;

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			arquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}

		foto.setNomeArquivo(novoNomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();

		NovaFoto novaFoto = NovaFoto.builder().nomeArquivo(foto.getNomeArquivo()).contentType(foto.getContentType()).size(foto.getTamanho()).inputStream(dadosArquivo).build();

		storageService.substituir(arquivoExistente, novaFoto);

		return foto;

	}

	@Transactional
	public FotoProduto buscar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoNaoEncontradaException(produtoId, restauranteId));
	}

	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {

		FotoProduto fotoExistente = buscar(restauranteId, produtoId);

		produtoRepository.delete(fotoExistente);
		produtoRepository.flush();

		storageService.remover(fotoExistente.getNomeArquivo());

	}

}
