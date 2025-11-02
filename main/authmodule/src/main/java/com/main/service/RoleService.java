package com.main.service;

import com.main.mapper.RoleMapper;
import com.main.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService
{
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

}
