package br.com.portfolio.algafood.config;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PageableInterpreter {
    public static Pageable interpreter(Pageable pageable, Map<String, String> fieldsMapping) {
        var orders = pageable.getSort()
                .stream()
                .filter(order -> fieldsMapping.containsKey(order.getProperty()))
                .map(order -> new Sort.Order(order.getDirection(),
                        fieldsMapping.get(order.getProperty())))
                .toList();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }
}
