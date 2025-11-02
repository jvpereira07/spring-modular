package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.mapper.RoleMapper;
import com.main.repository.RoleRepository;

@Service
public class RoleService
{
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final RoleCacheService roleCacheService;
    @Autowired
    public RoleService(RoleMapper roleMapper, RoleRepository roleRepository, RoleCacheService roleCacheService) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.roleCacheService = roleCacheService;
    }

}
