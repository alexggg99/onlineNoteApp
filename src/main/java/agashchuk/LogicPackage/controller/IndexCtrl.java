package agashchuk.LogicPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCtrl {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/note/test")
    public String note(Model model) {
        return "contact";
    }

    @GetMapping("/admin/test")
    public String admin(Model model) {
        return "contact";
    }
}
