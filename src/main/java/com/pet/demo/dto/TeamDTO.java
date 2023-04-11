package com.pet.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDTO {
    private Long id;
    private String unitName;
    private UserDTO unitHead;
    private List<UserDTO> users = new ArrayList<>();
}
