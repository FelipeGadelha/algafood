package br.com.portfolio.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

public interface ImageStorageService {
    void store(Image image);
    default String generateFileName(String originalName) { return UUID.randomUUID() + "_" + originalName; }

    record Image(String fileName, InputStream inputStream) { }
}
