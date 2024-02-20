package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.PermissionControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.PermissionRq;
import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs;
import br.com.portfolio.algafood.domain.service.PermissionService;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/permissions")
public class PermissionController implements PermissionControllerOpenApi {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    @JsonView(View.Basic.class)
    @Override public ResponseEntity<List<PermissionRs>> findAll() {
        return ResponseEntity.ok(permissionService.findAll()
                .stream()
                .map(PermissionRs::new)
                .toList());
    }

    @GetMapping("/{id}")
    @Override public ResponseEntity<PermissionRs> findById(@PathVariable Long id) {
        var permission = permissionService.findById(id);
        return ResponseEntity.ok(new PermissionRs(permission));
    }

    @PostMapping
    @Override public ResponseEntity<PermissionRs> save(@RequestBody @Valid PermissionRq permissionRq) {
        var permission = permissionService.save(permissionRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PermissionRs(permission));
    }

    @PutMapping("/{id}")
    @Override public ResponseEntity<PermissionRs> update(@PathVariable Long id, @RequestBody PermissionRq permissionRq) {
        var permission = permissionService.update(id, permissionRq.convert());
        return ResponseEntity.ok(new PermissionRs(permission));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void deleteById(@PathVariable Long id) { permissionService.deleteById(id); }
}
