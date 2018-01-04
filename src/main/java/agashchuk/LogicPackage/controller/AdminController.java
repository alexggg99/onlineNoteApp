package agashchuk.LogicPackage.controller;

import agashchuk.SystemSpecificPackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping(value = "/block")
    public String blockUser(@RequestParam(value = "id") long id, Model model){
        userService.blockUser(userService.findById(id));
        return "forward:/admin";
    }

    @GetMapping(value = "/unblock")
    public String unblockUser(@RequestParam(value = "id") long id, Model model){
        userService.unblockUser(userService.findById(id));
        return "forward:/admin";
    }

}
