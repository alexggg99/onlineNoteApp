package agashchuk.SystemSpecificPackage.controller;

import agashchuk.SystemSpecificPackage.exception.UserBlockedFound;
import agashchuk.SystemSpecificPackage.exception.UserNotFound;
import agashchuk.SystemSpecificPackage.exception.UserNotmatchFound;
import agashchuk.SystemSpecificPackage.model.AuthorizationRequest;
import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;
import agashchuk.SystemSpecificPackage.repo.UserRepository;
import agashchuk.SystemSpecificPackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rest/login/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/authorize")
    public boolean authorize(@Valid @RequestBody AuthorizationRequest authorizationRequest) {
        User user = userRepository.findByUsername(authorizationRequest.getUsername());
        if (user == null) {
            // throw exception 404, User not found
            throw new UserNotFound();
        }

        if(!userService.checkPassword(authorizationRequest.getPassword(), user.getPassword())) {
            throw new UserNotmatchFound();
        }

        if (user.getState() != UserState.Activated) {
            // throw exception 403, This user has been blocked
            throw new UserBlockedFound();
        }

        userService.authorize(user);

        return true;
    }

    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = userService.getCurrentLoginUser();
        if (currentUser != null) {
            new SecurityContextLogoutHandler().logout(request, null, null);
        }
    }

}
