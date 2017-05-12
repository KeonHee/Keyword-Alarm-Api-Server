package com.landvibe.rest.controller;

import com.landvibe.domain.User;
import com.landvibe.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user on 2017-05-02.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String findUser(@RequestParam(value = "name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name",name);
        return "user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User addUser(@RequestParam(value = "name") String name,
                        @RequestParam(value = "userId") String userId){
        User user = userService.addUser(name,userId);
        return user;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(Model model){
        List<User> users = userService.findall();
        return users;
    }

    @RequestMapping(value = "/userId",method = RequestMethod.GET)
    public User findUserId(@RequestParam(value="id", required=true) long id) {
        User user = userService.findById(id);
        return user;
    }


}
