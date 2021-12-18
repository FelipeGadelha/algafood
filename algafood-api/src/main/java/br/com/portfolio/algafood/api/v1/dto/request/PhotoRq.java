package br.com.portfolio.algafood.api.v1.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record PhotoRq(
    MultipartFile file,
    String description
){ }
