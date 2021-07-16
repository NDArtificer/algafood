package com.artificer.algafood.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.model.input.ImagemProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/imagem")
public class RestauranteProdutoImagemController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarImagem(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid ImagemProdutoInput produtoInput) {
		var nomeArquivo = UUID.randomUUID().toString() + "_" + produtoInput.getFile().getOriginalFilename();

		var arquivo = Path.of("C:\\Users\\Denilson\\Desktop\\Nova_pasta", nomeArquivo);

		try {
			produtoInput.getFile().transferTo(arquivo);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
