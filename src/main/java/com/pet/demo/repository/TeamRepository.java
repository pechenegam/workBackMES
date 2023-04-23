package com.pet.demo.repository;

import com.pet.demo.entity.Team;
import com.pet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>, JpaSpecificationExecutor<Team> {
    Optional<Team> findByUnitName(String unitName);

    Optional<Team> findById(Long id);

}

