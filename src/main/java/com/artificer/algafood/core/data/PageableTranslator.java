package com.artificer.algafood.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableTranslator {

	public static Pageable translate(Pageable page, Map<String, String> fields) {
		var orders = page.getSort().stream()
				.filter(order -> fields.containsKey(order.getProperty()))
				.map(order -> new Sort.Order(order.getDirection(), fields.get(order.getProperty())))
				.collect(Collectors.toList());

		return PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(orders));
	}

}
