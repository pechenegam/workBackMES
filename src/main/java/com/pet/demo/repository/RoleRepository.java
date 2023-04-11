package com.pet.demo.repository;

import com.pet.demo.entity.Role;
import com.pet.demo.utils.ERole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(ERole name);
}
