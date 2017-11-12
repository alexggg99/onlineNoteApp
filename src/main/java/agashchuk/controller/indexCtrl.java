package agashchuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Controller
public class indexCtrl {
    @GetMapping("/")
    public String index(ServletRequest request, ServletResponse response) {
        return "index";
    }
}
