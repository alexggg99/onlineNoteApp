package agashchuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexCtrl {
    @GetMapping("/")
    public String index(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        model.addAttribute("today", format.format(new Date()));
        return "index";
    }
}
