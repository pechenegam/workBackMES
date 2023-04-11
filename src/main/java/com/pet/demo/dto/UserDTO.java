package com.pet.demo.dto;

import com.pet.demo.entity.Role;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String fullName = firstName + " " + secondName;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
    private Set<Role> roles;
    private Long teamId;
}
