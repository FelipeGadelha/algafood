package br.com.portfolio.algafood.api.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PasswordRq (
    @Schema(example = "123", nullable = false)
    @NotBlank String password,
    @Schema(example = "123", nullable = false)
    @NotBlank String newPassword
) { }