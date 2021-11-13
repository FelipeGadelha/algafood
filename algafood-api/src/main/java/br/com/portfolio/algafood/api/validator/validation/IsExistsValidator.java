package br.com.portfolio.algafood.api.validator.validation;

import br.com.portfolio.algafood.api.validator.annotation.IsExists;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IsExistsValidator implements ConstraintValidator<IsExists, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(IsExists params) {
        domainAttribute = params.fieldId();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        List<?> list = manager
                .createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " =:pValue")
                .setParameter("pValue", value)
                .getResultList();
        Assert.isTrue(list.size() <= 1,
                "Não existe " + klass.getSimpleName() + " com o " + domainAttribute + " : " + value);
        return !list.isEmpty();
    }
}