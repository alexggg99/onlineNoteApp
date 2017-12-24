package agashchuk.SystemSpecificPackage.controller;

import agashchuk.SystemSpecificPackage.model.RegistrationRequest;
import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.service.UserService;
import agashchuk.SystemSpecificPackage.validators.FirstValidationGroupSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "signup")
    public ResponseEntity<Object> registerUser(@Validated(FirstValidationGroupSequence.class) @RequestBody RegistrationRequest request) {
        User user = userService.findUserByUsername(request.getUsername());
        if (user != null) {
            return new ResponseEntity<Object>("Username is occupied", new HttpHeaders(), HttpStatus.CONFLICT);
        }
        //create new user
        user =  userService.createUser(request);
        return new ResponseEntity<Object>("User '"+ user.getUsername() +"' is created. Check your email.", new HttpHeaders(), HttpStatus.CREATED);
    }
}
