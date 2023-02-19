package br.com.portfolio.algafood.config.storage;

import com.amazonaws.regions.Regions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {
    private Local local = new Local();
    private S3 s3 = new S3();
    private StorageType type = StorageType.S3;

    public enum StorageType { LOCAL, S3 }
    public class Local {
        private Path imagesDirectory;

        public Path getImagesDirectory() { return imagesDirectory; }
        public void setImagesDirectory(Path imagesDirectory) { this.imagesDirectory = imagesDirectory; }
    }
    public class S3 {
        private String accessKey;
        private String secretKey;
        private String bucket;
        private Regions region;
        private String directory;

        public String getAccessKey() { return accessKey; }
        public String getSecretKey() { return secretKey; }
        public String getBucket() { return bucket; }
        public Regions getRegion() { return region; }
        public String getDirectory() { return directory; }

        public void setAccessKey(String accessKey) { this.accessKey = accessKey; }
        public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
        public void setBucket(String bucket) { this.bucket = bucket; }
        public void setRegion(Regions region) { this.region = region; }
        public void setDirectory(String directory) { this.directory = directory; }
    }

    public Local getLocal() { return local; }
    public S3 getS3() { return s3; }
    public StorageType getType() { return type; }

    public void setLocal(Local local) { this.local = local; }
    public void setS3(S3 s3) { this.s3 = s3; }
    public void setType(StorageType type) { this.type = type; }
}
