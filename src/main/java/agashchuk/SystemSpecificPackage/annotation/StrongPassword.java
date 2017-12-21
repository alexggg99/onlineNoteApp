package agashchuk.SystemSpecificPackage.annotation;

import agashchuk.SystemSpecificPackage.validators.StrongPasswordValidator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@Size(min=6, max=40, message = "Password must contain {min} - {max} characters")
@Pattern.List({
    @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain one digit."),
    @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain one lowercase letter."),
    @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain one uppercase letter."),
//    @Pattern(regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+", message ="Password must contain one special character."),
    @Pattern(regexp = "(?=\\S+$).+", message = "Password must contain no whitespace.")
})
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

    String message() default "Password doesn't match bean validation constraints.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
