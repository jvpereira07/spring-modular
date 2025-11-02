package com.main.mapper;

import org.springframework.stereotype.Component;

import com.main.DTO.UserDTO;
import com.main.model.User;

@Component
public class UserMapper {
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setActive(userDTO.isActive());
        // Map other fields as necessary
        return user;
    }
    public UserDTO toDTO(User user){
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setActive(user.isActive());
        // Map other fields as necessary
        return userDTO;
    }
}
