package br.com.portfolio.algafood.infra.service;

import br.com.portfolio.algafood.domain.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalImageStorageService implements ImageStorageService {

    @Value("${algafood.storage.local}") private Path directory;

    @Override
    public void store(Image image) {
        try{
            var path = filePath(image.fileName());
            FileCopyUtils.copy(image.inputStream(), Files.newOutputStream(path));
        } catch (Exception e) {
            System.err.println(e.getCause());
            throw new RuntimeException("Não foi possivel armazenar arquivo.", e.getCause());
        }
    }
    private Path filePath(String fileName) { return directory.resolve(Path.of(fileName)); }
}
