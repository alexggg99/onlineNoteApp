package agashchuk.SystemSpecificPackage.controller;

import agashchuk.SystemSpecificPackage.model.RegistrationRequest;
import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;
import agashchuk.SystemSpecificPackage.service.UserService;
import agashchuk.SystemSpecificPackage.validators.FirstValidationGroupSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "/signup")
    public ResponseEntity<Object> registerUser(@Validated(FirstValidationGroupSequence.class) @RequestBody RegistrationRequest request) {
        User user = userService.findUserByUsername(request.getUsername());
        if (user != null) {
            return new ResponseEntity<Object>("Username is occupied", new HttpHeaders(), HttpStatus.CONFLICT);
        }
        //create new user
        user =  userService.createUser(request);
        return new ResponseEntity<Object>("User '"+ user.getUsername() +"' is created. Check your email.", new HttpHeaders(), HttpStatus.CREATED);
    }

    ///activate?code=a7091869-1ce5-4415-b001-97770d9e8876
    @GetMapping(value = "/activate")
    public String activateUser(@RequestParam("code") String activationCode, Model model) {
        User user = userService.findUserByActivationCode(activationCode);
        if (user == null) {
            model.addAttribute("message", "Opps, user was not found");
        } else {
            user.setActivationCode(null);
            user.setState(UserState.Activated);
            userService.save(user);
            model.addAttribute("message", "Congratulations, you have been just activated");
        }
        return "userActivation";
    }

}
