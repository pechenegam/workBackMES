package com.pet.demo.controller;

import com.pet.demo.dto.TeamDTO;
import com.pet.demo.dto.criteria.TeamCriteria;
import com.pet.demo.dto.request.TeamRequest;
import com.pet.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public List<TeamDTO> all(@RequestBody TeamCriteria criteria) {
        return teamService.getAllTeams(criteria);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TeamDTO getOneUserById(@PathVariable Long id) {
        return teamService.getOneTeamById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestBody TeamRequest teamRequest) {
        teamService.update(teamRequest);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody TeamRequest teamRequest) {
        teamService.save(teamRequest);
    }
}