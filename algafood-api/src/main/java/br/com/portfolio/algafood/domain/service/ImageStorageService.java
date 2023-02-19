package br.com.portfolio.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

public interface ImageStorageService {
    void store(Image image);
    void remove(String fileName);
    ImageRecover recover(String fileName);

    default String generateFileName(String originalName) { return UUID.randomUUID() + "_" + originalName; }

    default void replace(String fileName, Image image) {
        this.store(image);
        if (fileName != null) this.remove(fileName);
    }

    record Image(String fileName, String contentType, InputStream inputStream) { }
    record ImageRecover(Optional<InputStream> inputStream, Optional<String> url) { }
}
