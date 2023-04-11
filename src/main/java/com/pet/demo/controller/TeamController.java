package com.pet.demo.controller;

import com.pet.demo.dto.TeamDTO;
import com.pet.demo.dto.criteria.TeamCriteria;
import com.pet.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PutMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<TeamDTO> all(@RequestBody  TeamCriteria criteria) {
        return teamService.getAllTeams(criteria);
    }

    @GetMapping("/{id}")
    public TeamDTO getOneUserById(@PathVariable Long id) {
        return teamService.getOneTeamById(id);
    }

    @PutMapping("/{id}")
    public void delete(@PathVariable Long id) {
         teamService.delete(id);
    }
}