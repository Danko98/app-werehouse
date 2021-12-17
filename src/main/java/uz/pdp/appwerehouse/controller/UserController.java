package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.User;
import uz.pdp.appwerehouse.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

//CREATE
    @PostMapping
    public Result addUser(@RequestBody User userDto){
        return userService.addUser(userDto);
    }

//READ ALL
    @GetMapping
    public Page<User> getUserPage(@RequestParam int page){
        return userService.getUserPage(page);
    }

//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

//UPDATE
    @PutMapping(value = "/{id}")
    public Result editUser(@PathVariable Integer id,@RequestParam boolean active,@RequestBody User userDto){
        return userService.editUser(id, userDto, active);
    }

//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }


}
