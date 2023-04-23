package com.pet.demo.dto.response;

import com.pet.demo.dto.UserDTO;
import com.pet.demo.entity.Role;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
public class UserTeamCommonResponse {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String email;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
    private Set<Role> roles;
    private String unitName;
    private UserDTO unitHead;
    private Long teamId;
}
