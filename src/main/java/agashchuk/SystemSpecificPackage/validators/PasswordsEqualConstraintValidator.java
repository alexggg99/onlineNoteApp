package agashchuk.SystemSpecificPackage.validators;

import agashchuk.SystemSpecificPackage.annotation.PasswordsEqualConstraint;
import agashchuk.SystemSpecificPackage.model.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, Object>{
    @Override
    public void initialize(PasswordsEqualConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        RegistrationRequest registrationRequest = (RegistrationRequest) value;
        return (registrationRequest.getPassword().equals(registrationRequest.getPasswordConfirm()));
    }
}
