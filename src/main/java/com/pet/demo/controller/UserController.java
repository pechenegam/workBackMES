package com.pet.demo.controller;

import com.pet.demo.dto.UserDTO;
import com.pet.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getOneUserById(@PathVariable Long id) {
        return userService.getOneUserById(id);
    }

    @PutMapping("/{id}")
    public void delete(@PathVariable Long id) {
         userService.delete(id);
    }
}