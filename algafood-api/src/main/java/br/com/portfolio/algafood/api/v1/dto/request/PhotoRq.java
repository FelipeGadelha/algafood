package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.api.validator.annotation.FileSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PhotoRq{
    @NotNull @FileSize(max = "500KB") private MultipartFile file;
    @NotBlank private String description;

    public PhotoRq(MultipartFile file, String description) {
        this.file = file;
        this.description = description;
    }

    public MultipartFile getFile() { return file; }
    public String getDescription() { return description; }
}