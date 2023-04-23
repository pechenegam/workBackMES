package com.pet.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
