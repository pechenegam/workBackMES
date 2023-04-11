package com.pet.demo.service;

import com.pet.demo.dto.TeamDTO;
import com.pet.demo.dto.criteria.TeamCriteria;
import com.pet.demo.dto.request.TeamRequest;
import com.pet.demo.entity.Team;
import com.pet.demo.exception.EntityNotFoundException;
import com.pet.demo.mappers.TeamMapper;
import com.pet.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static com.pet.demo.Specification.TeamSpec.*;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private static final String EXCEPTION_SERVICE = "Team";
    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public List<TeamDTO> getAllTeams(TeamCriteria criteria) {
        Pageable pageable = createPageable(criteria);

        Specification<Team> spec = hasUnitName(criteria.getUnitName());
        spec = spec.and(hasHeadFirstName(criteria.getHeadFirstName()));
        spec = spec.and(hasHeadSecondName(criteria.getHeadSecondName()));
        spec = spec.and(isDeleteDateNull());
        List<Team> teams = teamRepository.findAll(spec, pageable).getContent();
        return teamMapper.destinationToSourceList(teams);
    }

    private Pageable createPageable(TeamCriteria criteria) {
        if (criteria.getSort() != null && !criteria.getSort().isBlank()) {
            Sort sort = getSort(criteria);
            return PageRequest.of(criteria.getPage(), criteria.getSize(), sort);
        } else {
            return PageRequest.of(criteria.getPage(), criteria.getSize());
        }
    }

    private Sort getSort(TeamCriteria criteria) {
        String[] sortParams = criteria.getSort().split(",");
        Sort sort;

        switch (sortParams[0]) {
            case "unitName":
                sort = Sort.by("unitName");
                break;
            case "firstName":
                sort = Sort.by("unitHead.firstName");
                break;
            case "secondName":
                sort = Sort.by("unitHead.secondName");
                break;
            default:
                sort = Sort.by(sortParams[0]);
                break;
        }

        if (sortParams[1].equals("desc")) {
            sort = sort.descending();
        } else if (sortParams[1].equals("asc")) {
            sort = sort.ascending();
        }
        return sort;
    }

    public TeamDTO findByName(String name) {
        Team team = teamRepository.findByUnitName(name).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, name));
        return teamMapper.destinationToSource(team);
    }

    public TeamDTO getOneTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, id));
        return teamMapper.destinationToSource(team);
    }

    @Transactional
    public void delete(Long id) {
      Team team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, id));
      team.setDeleteDate(OffsetDateTime.now());
      teamRepository.save(team);
    }

    @Transactional
    public void update(TeamRequest request) {
        Team team = teamRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, request.getId()));
        team.setUnitName(request.getUnitName());
//        team.setUnitHead(request.getHeadId());
        teamRepository.save(team);
    }
}
