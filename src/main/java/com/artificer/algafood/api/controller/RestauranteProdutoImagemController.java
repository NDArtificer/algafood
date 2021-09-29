package com.artificer.algafood.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artificer.algafood.api.converter.model.ImagemProdutoModelConverter;
import com.artificer.algafood.api.model.ImagemProdutoModel;
import com.artificer.algafood.api.model.input.ImagemProdutoInput;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.artificer.algafood.domain.model.FotoProduto;
import com.artificer.algafood.domain.model.Produto;
import com.artificer.algafood.domain.service.CadastroProdutoService;
import com.artificer.algafood.domain.service.CatalogoImagemProdutoService;
import com.artificer.algafood.domain.service.FotoStorageService;
import com.artificer.algafood.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/imagem")
public class RestauranteProdutoImagemController {
	@Autowired
	private CadastroProdutoService produtoService;

	@Autowired
	private ImagemProdutoModelConverter modelConverter;

	@Autowired
	private FotoStorageService fotoService;

	@Autowired
	private CatalogoImagemProdutoService imagemProdutoService;
	
	
	@Autowired
	private ApiLinks apiLinks;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ImagemProdutoModel atualizarImagem(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid ImagemProdutoInput produtoInput) throws IOException {
		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

		MultipartFile file = produtoInput.getFile();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(produtoInput.getDescricao());
		foto.setContentType(file.getContentType());
		foto.setTamanho(file.getSize());
		foto.setNomeArquivo(file.getOriginalFilename());

		return modelConverter.toModel(imagemProdutoService.salvar(foto, file.getInputStream()))
				.add(apiLinks.linkToProduto(restauranteId, produtoId, "Produto"))
				.add(apiLinks.linkToFotoProduto(restauranteId, produtoId));
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirImagem(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		imagemProdutoService.excluir(restauranteId, produtoId);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ImagemProdutoModel buscarImagem(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

		return modelConverter.toModel(imagemProdutoService.buscar(restauranteId, produtoId));
	}

	@GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> servirImagem(@PathVariable Long restauranteId,
			@RequestHeader(name = "accept") String acceptHeader, @PathVariable Long produtoId)
			throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto foto = imagemProdutoService.buscar(restauranteId, produtoId);

			MediaType mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
			List<MediaType> mediasAceitas = MediaType.parseMediaTypes(acceptHeader);

			verificarCompatibilidadeMediaType(mediaTypeFoto, mediasAceitas);

			FotoRecuperada inputStream = fotoService.recuperar(foto.getNomeArquivo());


			if (inputStream.hasUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, inputStream.getUrl())
						.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(inputStream.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediasAceitas)
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediasAceitas.stream()
				.anyMatch(mediasAceita -> mediasAceita.isCompatibleWith(mediaTypeFoto));
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediasAceitas);
		}
	}

}
