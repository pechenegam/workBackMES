package com.pet.demo.service;

import com.pet.demo.dto.UserDTO;
import com.pet.demo.dto.criteria.UserCriteria;
import com.pet.demo.dto.request.UserRequest;
import com.pet.demo.dto.response.UserTeamCommonResponse;
import com.pet.demo.entity.Team;
import com.pet.demo.entity.User;
import com.pet.demo.exception.EntityNotFoundException;
import com.pet.demo.mappers.UserMapper;
import com.pet.demo.repository.TeamRepository;
import com.pet.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

import static com.pet.demo.Specification.UserSpec.*;


@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserService {

    private static final String EXCEPTION_SERVICE = "User";
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    private final UserMapper userMapper;

    public List<UserTeamCommonResponse> all(UserCriteria criteria) {
        Specification<User> spec = isDeleteDateNull();
        spec = setSpecFromCriteria(criteria, spec);
        List<User> users = userRepository.findAll(spec);
        return userMapper.destinationToSourceListCommon(users);
    }

    private Specification<User> setSpecFromCriteria(UserCriteria criteria, Specification<User> spec) {
        if(criteria.getFirstName() != null){
            spec = spec.and(hasFirstName(criteria.getFirstName()));
        }
        if(criteria.getSecondName() != null){
            spec = spec.and(hasSecondName(criteria.getSecondName()));
        }
        if(criteria.getUnitName() != null){
            spec = spec.and(hasUnitName(criteria.getUnitName()));
        }
        if(criteria.getEmail() != null){
            spec = spec.and(hasEmail(criteria.getEmail()));
        }
        if(criteria.getUsername() != null){
            spec = spec.and(hasUserName(criteria.getUsername()));
        }
        return spec;
    }

    public UserDTO findByName(String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, name));
        return userMapper.destinationToSource(user);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, id));
        return userMapper.destinationToSource(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, id));
        user.setDeleteDate(OffsetDateTime.now());
        userRepository.save(user);
        log.info("user with id: " + user.getId() + " was deleted");
    }

    @Transactional
    public void update(UserRequest userRequest) {

        Long teamId = userRequest.getTeamId();

        User user = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, teamId));
        log.info("user update start, id: " + user.getId());
        user.setFirstName(userRequest.getFirstName());
        user.setSecondName(userRequest.getSecondName());
        user.setEmail(userRequest.getEmail());
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, teamId));
        user.setTeam(team);
        userRepository.save(user);
        log.info("user update end");
    }
}
