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
}
