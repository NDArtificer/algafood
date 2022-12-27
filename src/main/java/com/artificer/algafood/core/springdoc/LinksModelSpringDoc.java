package com.artificer.algafood.core.springdoc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksModelSpringDoc {

	private LinkModel rel;

	@Getter
	@Setter
	private class LinkModel {
		private String href;
		private boolean templated;
	}

}
