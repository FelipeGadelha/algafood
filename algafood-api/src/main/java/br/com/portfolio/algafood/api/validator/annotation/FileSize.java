package br.com.portfolio.algafood.api.validator.annotation;

import br.com.portfolio.algafood.api.validator.validation.IsExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {IsExistsValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {

    String message() default "tamanho do arquivo inválido";

    Class<?> [] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String max();
}