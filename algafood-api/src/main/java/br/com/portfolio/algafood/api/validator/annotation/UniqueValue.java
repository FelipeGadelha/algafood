package br.com.portfolio.algafood.api.validator.annotation;

import br.com.portfolio.algafood.api.validator.validation.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "br.com.portfolio.algafood.api.validator.validation.UniqueValueValidator";

    Class<?> [] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();
}
