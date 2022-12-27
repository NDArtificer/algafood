package com.artificer.algafood.api.converter.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.artificer.algafood.api.controller.CidadeController;
import com.artificer.algafood.api.controller.EstadoController;
import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.domain.model.Cidade;

@Component
public class CidadeModelConverter extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {
	
	@Autowired
	private ModelMapper modelMapper;

	public CidadeModelConverter() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade Cidade) {
		CidadeModel cidadeModel = modelMapper.map(Cidade, CidadeModel.class);

		cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));

		cidadeModel.getEstado()
				.add(linkTo(methodOn(EstadoController.class).buscar(cidadeModel.getEstado().getId())).withSelfRel());

		return cidadeModel;
	}

//	public List<CidadeModel> toColletionModel(List<Cidade> cidades) {
//		return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
//	}

}
