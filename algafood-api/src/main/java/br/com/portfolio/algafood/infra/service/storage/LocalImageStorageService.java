package br.com.portfolio.algafood.infra.service.storage;

import br.com.portfolio.algafood.config.storage.StorageProperties;
import br.com.portfolio.algafood.domain.exception.StorageException;
import br.com.portfolio.algafood.domain.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class LocalImageStorageService implements ImageStorageService {

    private final StorageProperties properties;

    @Autowired
    public LocalImageStorageService(StorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public void store(Image image) {
        try{
            var filePath = filePath(image.fileName());
            FileCopyUtils.copy(image.inputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e.getCause());
        }
    }

    @Override
    public void remove(String fileName) {
        try{
            var filePath = filePath(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo.", e.getCause());
        }
    }

    @Override
    public ImageRecover recover(String fileName) {
        try {
            var filePath = filePath(fileName);
            return new ImageRecover(Optional.of(Files.newInputStream(filePath)), Optional.empty());
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    private Path filePath(String fileName) {
        return properties
        .getLocal()
            .getImagesDirectory()
            .resolve(Path.of(fileName));
    }
}
