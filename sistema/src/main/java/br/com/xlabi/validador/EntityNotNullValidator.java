package br.com.xlabi.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

 class EntityNotNullValidator implements ConstraintValidator<EntityNotNull, Object> {

    EntityNotNull entityNotNull;

    @Override
    public void initialize(EntityNotNull entityNotNull) {
        this.entityNotNull = entityNotNull;

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if(entityNotNull != null && !entityNotNull.toString().isEmpty())
        	return true;
        
        return false;
    	
    	
    }
}
