package com.pet.demo.Specification;

import com.pet.demo.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

import static java.util.Objects.requireNonNullElse;

public class UserSpec {
    public static Specification<User> hasUserName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), getPatternLike(name));
    }

    public static Specification<User> hasUnitName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("team").get("unitName")), getPatternLike(name));
    }

    public static Specification<User> hasFirstName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), getPatternLike(name));
    }

    public static Specification<User> hasSecondName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("secondName")), getPatternLike(name));
    }

    public static Specification<User> hasEmail(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), getPatternLike(name));
    }

    public static Specification<User> isDeleteDateNull() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNull(root.get("deleteDate"));
    }

    private static String getPatternLike(String name) {
        name = requireNonNullElse(name, "");
        return "%" + name.toLowerCase(Locale.ROOT) + "%";
    }
}
