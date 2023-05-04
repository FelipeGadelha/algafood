package br.com.portfolio.algafood.config.storage;

import br.com.portfolio.algafood.domain.service.ImageStorageService;
import br.com.portfolio.algafood.infra.service.storage.LocalImageStorageService;
import br.com.portfolio.algafood.infra.service.storage.S3ImageStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    private final StorageProperties properties;

    private static final Logger log = LoggerFactory.getLogger(StorageConfig.class);

    @Autowired
    public StorageConfig(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
            properties.getS3().getAccessKey(),
            properties.getS3().getSecretKey()
        );
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(properties.getS3().getRegion())
            .build();
    }

    @Bean
    public ImageStorageService imageStorageService() {
        if (properties.getType() == StorageProperties.StorageType.S3) {
            log.info("Storage properties type {}", properties.getType());
            return new S3ImageStorageService(amazonS3(), properties);
        } else {
            log.info("Storage properties type {}", properties.getType());
            return new LocalImageStorageService(properties);
        }
    }
}
