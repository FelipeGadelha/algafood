package br.com.portfolio.algafood.infra.service.storage;

import br.com.portfolio.algafood.config.storage.StorageProperties;
import br.com.portfolio.algafood.domain.exception.StorageException;
import br.com.portfolio.algafood.domain.service.ImageStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Optional;

public class S3ImageStorageService implements ImageStorageService {

    private final AmazonS3 amazonS3;
    private final StorageProperties properties;

    @Autowired
    public S3ImageStorageService(AmazonS3 amazonS3, StorageProperties properties) {
        this.amazonS3 = amazonS3;
        this.properties = properties;
    }

    @Override
    public void store(Image image) {
        try {
            final var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(image.contentType());
            var putObjectRequest = new PutObjectRequest(
                properties.getS3().getBucket(),
                getFilePath(image.fileName()),
                image.inputStream(),
                objectMetadata
            )
            .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            var deleteObjectRequest = new DeleteObjectRequest(properties.getS3().getBucket(), getFilePath(fileName));
            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }

    @Override
    public ImageRecover recover(String fileName) {
        String filePath = getFilePath(fileName);
        URL url = amazonS3.getUrl(properties.getS3().getBucket(), filePath);
        return new ImageRecover(Optional.empty(), Optional.ofNullable(url.toString()));
    }

    private String getFilePath(String filename) {
        return String.format("%s/%s", properties.getS3().getDirectory(), filename);
    }
}
