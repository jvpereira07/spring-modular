package com.main.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.DTO.UserDTO;
import com.main.mapper.UserMapper;
import com.main.model.User;
import com.main.repository.UserRepository;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserDTO> get(int page, int size, String sortBy, String sortDir, Map<String, String> filters) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        PageRequest pageable = PageRequest.of(page, size, sort);

        Specification<User> spec = null;
        if (filters != null) {
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();

                if (value == null || value.isBlank()) continue;
                if ("page".equals(field) || "size".equals(field) || "sortBy".equals(field) || "sortDir".equals(field)) continue;

                Specification<User> s = (root, query, cb) ->
                        cb.like(cb.lower(root.get(field).as(String.class)), "%" + value.toLowerCase() + "%");
                spec = (spec == null) ? s : spec.and(s);
            }
        }

        Page<User> result = (spec == null)
                ? userRepository.findAll(pageable)
                : userRepository.findAll(spec, pageable);

        return result.map(userMapper::toDTO);
    }
    public UserDTO getById(UUID id){
        User user = userRepository.findById(id).orElse(null);
        return userMapper.toDTO(user);
    }
    public UserDTO create(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
    public UserDTO update(UUID id, UserDTO userDTO){
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser);
    }
    public boolean delete(UUID id){
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
