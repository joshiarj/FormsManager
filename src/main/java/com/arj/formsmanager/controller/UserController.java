package com.arj.formsmanager.controller;

import com.arj.formsmanager.dao.UserDAO;
import com.arj.formsmanager.dto.UserDTO;
import com.arj.formsmanager.entity.User;
import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping( value = "/register", method = RequestMethod.GET)
    public String index(){
        return "user/register";
    }
    
    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    public String save(UserDTO uDTO){
        User u = new User();
        u.setUsername(uDTO.getUsername());
        u.setPassword(uDTO.getPassword());
        u.setEmail(uDTO.getEmail());
        userDAO.insert(u);
        return "redirect:/user/register?success";
    }
    
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody String search(@RequestBody String name){
        
//    public @ResponseBody User search(@RequestParam(defaultValue = "adduser") String adduser){
        return name;
    }
}
