package br.com.portfolio.algafood.api.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@Pattern(regexp = "[+-]([0][0-9]|[1][0-2]):00",
        message = "Padrão de offset inválido.")
public @interface Offset {

    String message() default "offset inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}