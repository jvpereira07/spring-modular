package com.main.mapper;

import com.main.DTO.RoleDTO;
import com.main.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper
{
    public Role toEntity(RoleDTO dto){
        if (dto == null){
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setCode(dto.getCode());
        role.setLevel(dto.getLevel());
        role.setName(dto.getName());
        return role;

    }
    public RoleDTO toDTO(Role role){
        if (role == null){
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setCode(role.getCode());
        dto.setLevel(role.getLevel());
        dto.setName(role.getName());
        return dto;
    }
}
