//package web.controller;
//
//import web.service.UserService;
//import web.models.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String showUsersPage(@RequestParam(name = "locale", defaultValue = "en", required = false) String locale,
//                                @RequestParam(name = "count", defaultValue = "-1", required = false) int count,
//                                Model model) {
//        List<User> usersToDisplay;
//        if (count >= 0 && count < 5) {
//            usersToDisplay = userService.getSpecifiedNumberOfUsers(count);  // Исправлено на getSpecifiedNumberOfUsers
//        } else {
//            usersToDisplay = userService.getAllUsers();  // Исправлено на getAllUsers
//        }
//        userService.addUsersToModel(model, usersToDisplay);  // Исправлено на addUsersToModel
//        return "users";
//    }
//}




//package web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import web.models.User;
//import web.service.UserService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // Получение всех пользователей
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    // Добавление нового пользователя
//    @PostMapping("/addUser")
//    public void addUser(@RequestBody User user) {
//        userService.addUser(user);
//    }
//
//    // Имитация изменения пользователя через POST
//    @PostMapping("/updateUser")
//    public void updateUser(@RequestParam int userId, @RequestBody User user) {
//        userService.updateUser(user);
//    }
//
//    // Имитация удаления пользователя через POST
//    @PostMapping("/deleteUser")
//    public void deleteUser(@RequestParam int userId) {
//        userService.deleteUser(userId);
//    }
//}





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
