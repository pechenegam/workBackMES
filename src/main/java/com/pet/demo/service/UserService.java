package com.pet.demo.service;

import com.pet.demo.dto.UserDTO;
import com.pet.demo.entity.User;
import com.pet.demo.exception.EntityNotFoundException;
import com.pet.demo.mappers.UserMapper;
import com.pet.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private static final String EXCEPTION_SERVICE = "User";
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.destinationToSourceList(users);
    }

    public UserDTO findByName(String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, name));
        return userMapper.destinationToSource(user);
    }

    public UserDTO getOneUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EXCEPTION_SERVICE, id));
        return userMapper.destinationToSource(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
