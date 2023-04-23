package com.pet.demo.controller;

import com.pet.demo.dto.UserDTO;
import com.pet.demo.dto.criteria.UserCriteria;
import com.pet.demo.dto.request.UserRequest;
import com.pet.demo.dto.response.UserTeamCommonResponse;
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

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public List<UserTeamCommonResponse> all(@RequestBody UserCriteria criteria) {
        return userService.all(criteria);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
         userService.delete(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody UserRequest userRequest) {
        userService.update(userRequest);
    }
}