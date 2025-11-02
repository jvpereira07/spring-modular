package com.main.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.model.Permission;
import com.main.model.Role;
import com.main.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.security.Permissions;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RoleCacheService {
    private final RedisTemplate<String,String> redisTemplate;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    private String key(String roleName){
        return "roles:" + roleName;
    }
    public List<String> getPermissionsByRole(String roleName) {
        String cache = redisTemplate.opsForValue().get(key(roleName));
        if (cache != null) {
            try {
                return objectMapper.readValue(cache, new TypeReference<List<String>>() {
                });
            } catch (Exception e) {
                redisTemplate.delete(key(roleName)); // deletes corrupted caches
            }

        }
        Role role = roleRepository.findByCode(roleName);
        List<String> permissions = role.getPermissions().stream().map(Permission::getCode).toList();
        try {
            redisTemplate.opsForValue().set(key(roleName),
            objectMapper.writeValueAsString(permissions));
        } catch(Exception e){
            throw new RuntimeException("Error, cant serialize permissions");
        }
        return permissions;
    }
    public void invalidateRole(String roleName){
        redisTemplate.delete(key(roleName));
    }
    public void refreshRole(String roleName){
        invalidateRole(roleName);
        getPermissionsByRole(roleName);
    }



}
