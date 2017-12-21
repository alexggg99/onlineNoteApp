package agashchuk.SystemSpecificPackage.validators;

import agashchuk.SystemSpecificPackage.model.RegistrationRequest;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, RegistrationRequest.ValidationStepOne.class,
        RegistrationRequest.ValidationStepTwo.class,
        RegistrationRequest.ValidationStepThree.class,
RegistrationRequest.ValidationStepFour.class})
public interface FirstValidationGroupSequence {
}
