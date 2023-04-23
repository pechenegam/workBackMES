package com.pet.demo.mappers;

import com.pet.demo.dto.UserDTO;
import com.pet.demo.dto.response.UserTeamCommonResponse;
import com.pet.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    @Mapping(source = "team.id", target = "teamId")
    UserDTO destinationToSource(User destination);

    User sourceToDestination(UserDTO destination);

    List<UserDTO> destinationToSourceList(List<User> users);

    List<UserTeamCommonResponse> destinationToSourceListCommon(List<User> users);

    @Mapping(source = "team.unitHead", target = "unitHead")
    @Mapping(source = "team.unitName", target = "unitName")
    @Mapping(source = "team.id", target = "teamId")
//    @Mapping(source = "team.unitName", target = "unitName")
    UserTeamCommonResponse destinationToSourceCommon(User destination);
}
