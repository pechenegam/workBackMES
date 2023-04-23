package com.pet.demo.Specification;

import com.pet.demo.entity.Team;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

import static java.util.Objects.requireNonNullElse;

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
        name = requireNonNullElse(name, "");
        return "%" + name.toLowerCase(Locale.ROOT) + "%";
    }
}
