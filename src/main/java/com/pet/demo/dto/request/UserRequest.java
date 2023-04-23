package com.pet.demo.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String email;
    private Long teamId;
}
