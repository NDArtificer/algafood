package com.artificer.algafood.core.openapi;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagebleModel {

	private Integer page;
	private Integer size;
	private List<String> sort;
	
}
