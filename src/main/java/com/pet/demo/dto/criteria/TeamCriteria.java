package com.pet.demo.dto.criteria;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TeamCriteria {
    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 20;

    @Builder.Default
    private String unitName = "";

    @Builder.Default
    private String headFirstName = "";

    @Builder.Default
    private String headSecondName = "";

    private String sort;
}
