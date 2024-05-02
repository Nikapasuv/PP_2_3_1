package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("table_name", "Users");
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findUser(id));
        return "/editUser";
    }

    @PatchMapping()
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("delete_text", "Are you sure you want to delete the user?");
        model.addAttribute("user", userService.findUser(id));
        return "/deleteUser";
    }

    @DeleteMapping()
    public String delete(@ModelAttribute("user") User user) {
        userService.deleteUser(user.getId());
        return "redirect:/users";
    }
}
