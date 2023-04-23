package com.pet.demo.dto.criteria;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserCriteria {
    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 20;

    private String username;

    private String firstName;

    private String secondName;

    private String email;

    private String unitName;

    private String sort;
}