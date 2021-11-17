package br.com.portfolio.algafood.api.validator.annotation;

import br.com.portfolio.algafood.api.validator.validation.IsExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {IsExistsValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExists {

    String message() default "br.com.zupacademy.felipe.gadelha.casadocodigo.api.validator.IsExistsValidator";

    Class<?> [] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldId();

    Class<?> domainClass();
}