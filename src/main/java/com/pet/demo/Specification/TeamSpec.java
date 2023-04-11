package com.pet.demo.Specification;

import com.pet.demo.entity.Team;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.Locale;

public class TeamSpec {
    public static Specification<Team> hasUnitName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("unitName")), getPatternLike(name));
    }

    public static Specification<Team> hasHeadFirstName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("unitHead").get("firstName")), getPatternLike(name));
    }

    public static Specification<Team> hasHeadSecondName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("unitHead").get("secondName")), getPatternLike(name));
    }

    public static Specification<Team> isDeleteDateNull() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNull(root.get("deleteDate"));
    }

    private static String getPatternLike(String name) {
        return "%" + name.toLowerCase(Locale.ROOT) + "%";
    }
}
