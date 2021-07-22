package com.artificer.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artificer.algafood.api.converter.model.ImagemProdutoModelConverter;
import com.artificer.algafood.api.model.ImagemProdutoModel;
import com.artificer.algafood.api.model.input.ImagemProdutoInput;
import com.artificer.algafood.domain.model.FotoProduto;
import com.artificer.algafood.domain.model.Produto;
import com.artificer.algafood.domain.service.CadastroProdutoService;
import com.artificer.algafood.domain.service.CatalogoImagemProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/imagem")
public class RestauranteProdutoImagemController {
	@Autowired
	private CadastroProdutoService produtoService;
	
	@Autowired
	private ImagemProdutoModelConverter modelConverter;

	@Autowired
	private CatalogoImagemProdutoService imagemProdutoService;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ImagemProdutoModel atualizarImagem(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid ImagemProdutoInput produtoInput) {
		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

		MultipartFile file = produtoInput.getFile();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(produtoInput.getDescricao());
		foto.setContentType(file.getContentType());
		foto.setTamanho(file.getSize());
		foto.setNomeArquivo(file.getOriginalFilename());

		return modelConverter.toModel(imagemProdutoService.salvar(foto));
	}

}
