package br.com.portfolio.algafood.api.validator.annotation;

import br.com.portfolio.algafood.api.validator.validation.FileContentTypeValidator;
import br.com.portfolio.algafood.api.validator.validation.FileSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {FileContentTypeValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileContentType {
    String message() default "tipo de arquivo inválido";

    Class<?> [] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] allowed();
}
