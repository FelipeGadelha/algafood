package br.com.portfolio.algafood.api.v1.controller.doc.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class PagedOpenApi<T> {

    List<T> content;

    @Schema(example = "10", description = "Quantidade de registros por página")
    Long size;

    @Schema(example = "50", description = "Total de registros")
    Long totalElements;

    @Schema(example = "5", description = "Total de páginas")
    Long totalPages;

    @Schema(example = "0", description = "Número da página (começa em 0)")
    Long number;

    @Schema(example = "2", description = "Número de elementos por página")
    Long numberOfElements;

    public PagedOpenApi() {}

    public PagedOpenApi(List<T> content, Long size, Long totalElements, Long totalPages, Long number, Long numberOfElements) {
        this.content = content;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.number = number;
        this.numberOfElements = numberOfElements;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public List<T> getContent() {
        return content;
    }

    public Long getSize() {
        return size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public Long getNumber() {
        return number;
    }

    public Long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
