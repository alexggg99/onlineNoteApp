package agashchuk.SystemSpecificPackage.controller;

import agashchuk.SystemSpecificPackage.repo.UserRepository;
import agashchuk.SystemSpecificPackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/errors/403_access_denied")
    public  String accessDenied(@RequestParam String redirectUrl, Model model) {
        return "/errors/403_access_denied";
    }

    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        boolean isUserLogged = userService.isUserLogged();
        if (isUserLogged) {
            request.getSession().invalidate();
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("java-remember-me")) {
                    //Clear cookie
                    cookie.setMaxAge(0);
                    cookie.setValue(null);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
        }
    }

}
