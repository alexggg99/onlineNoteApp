package agashchuk.SystemSpecificPackage.model;


import agashchuk.SystemSpecificPackage.annotation.PasswordsEqualConstraint;
import agashchuk.SystemSpecificPackage.annotation.StrongPassword;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;


@PasswordsEqualConstraint(message = "Passwords are not equal", groups = RegistrationRequest.ValidationStepFour.class)
public class RegistrationRequest {
    @NotEmpty(groups = ValidationStepOne.class, message = "Username cant be empty")
    @Size.List({
            @Size(min=6, message="The Username field must be at least {min} characters", groups = ValidationStepTwo.class),
            @Size(max=30, message="The Username field must be less than {max} characters", groups = ValidationStepTwo.class)
    })
    private String username;
    @NotEmpty(message = "Email cant be empty", groups = ValidationStepOne.class)
    private String email;
    @NotEmpty(groups = ValidationStepThree.class, message = "Password cant be empty")
    @StrongPassword(groups = ValidationStepFour.class)
    private String password;
    @NotEmpty(groups = ValidationStepThree.class, message = "Password confirm cant be empty")
    private String passwordConfirm;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public interface ValidationStepOne{};
    public interface ValidationStepTwo{};
    public interface ValidationStepThree{};
    public interface ValidationStepFour{};
}
