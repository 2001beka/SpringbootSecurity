package springboot.peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.peaksoft.model.User;
import springboot.peaksoft.model.UserRole;
import springboot.peaksoft.service.RoleUserService;
import springboot.peaksoft.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleUserService roleService;
    @Autowired
    public UserController(UserService userService, RoleUserService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model){
        User user = userService.getByName(principal.getName());
        model.addAttribute("user", user);
        return "user_page";
    }


    @GetMapping("/admin")
    public String listUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/add")
    public String createUserForm(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", user);
        List<UserRole> roles = roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "add";
    }

    @PostMapping("/add")
    public String createUser(User user, @RequestParam Map<String, String> form){
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        List<UserRole> roles = roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user")User user, @RequestParam Map<String, String> form){
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(userService.getById(id));
        return "redirect:/admin";
    }
}
