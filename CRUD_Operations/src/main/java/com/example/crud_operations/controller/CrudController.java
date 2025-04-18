package com.example.crud_operations.controller;

import com.example.crud_operations.dto.UserDTO;
import com.example.crud_operations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CrudController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public String testMyProject()
    {
        return "Hellow Spring Boot...!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO)
    {
        return userService.saveUser(userDTO);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id)
    {
        return  ResponseEntity.ok(userService.fetchUserById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserById(@RequestBody UserDTO userDTO,@PathVariable("id") Long id)
    {
        return  ResponseEntity.ok(userService.updateUserById(userDTO,id));
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") Long id)
    {
        return  ResponseEntity.ok(userService.deleteUserById(id));
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<List<UserDTO>> fetchAllUser()
    {
        return  ResponseEntity.ok(userService.fetchAllUser());
    }

}
