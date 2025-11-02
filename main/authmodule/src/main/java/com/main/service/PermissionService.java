package com.main.service;

import com.main.mapper.PermissionMapper;
import com.main.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final PermissionMapper permissionMapper;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PermissionService(PermissionMapper permissionMapper, PermissionRepository permissionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.permissionMapper = permissionMapper;
        this.permissionRepository = permissionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


}
