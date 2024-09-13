package org.codehustle.ums.controller;

import org.codehustle.ums.entity.User;
import org.codehustle.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private UserService userService;

    @GetMapping(value = "home")
    public String loadUsers(Model model){
        model.addAttribute("userList",userService.getUsers());
        return "index";
    }

    @PostMapping(value = "/add")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "redirect:/home";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
