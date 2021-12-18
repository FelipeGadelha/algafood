package br.com.portfolio.algafood.api.v1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestParam MultipartFile file) {
        var fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        var filePhoto = Path.of("/home/felipesilva/Documentos/dev/test-image", fileName);

        System.err.println(filePhoto);
        System.err.println(file.getContentType());

        try { file.transferTo(filePhoto); } catch (Exception e) { throw new RuntimeException(e); }
    }
}
