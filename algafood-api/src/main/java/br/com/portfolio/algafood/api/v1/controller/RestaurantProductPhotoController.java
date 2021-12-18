package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.PhotoRq;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void update(@PathVariable Long restaurantId, @PathVariable Long productId, PhotoRq photoRq) {
        var fileName = UUID.randomUUID() + "_" + photoRq.file().getOriginalFilename();
        var filePhoto = Path.of("/home/felipesilva/Documentos/dev/test-image", fileName);

        System.err.println(photoRq.description());
        System.err.println(filePhoto);
        System.err.println(photoRq.file().getContentType());

        try { photoRq.file().transferTo(filePhoto); } catch (Exception e) { throw new RuntimeException(e); }
    }
}
