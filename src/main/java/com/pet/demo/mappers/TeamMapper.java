package com.pet.demo.mappers;

import com.pet.demo.dto.TeamDTO;
import com.pet.demo.dto.UserDTO;
import com.pet.demo.entity.Team;
import com.pet.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TeamMapper {

    TeamDTO destinationToSource(Team destination);

    Team sourceToDestination(TeamDTO destination);

    List<TeamDTO> destinationToSourceList(List<Team> teams);

    List<Team> sourceToDestinationList(List<TeamDTO> users);

}
