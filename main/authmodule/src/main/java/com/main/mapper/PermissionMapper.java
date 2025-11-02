package com.main.mapper;

import com.main.DTO.PermissionDTO;
import com.main.model.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public Permission toEntity(PermissionDTO dto){
        if( dto == null){
            return null;
        }
        Permission permission = new Permission();
        permission.setCode(dto.getCode());
        permission.setId(dto.getCode());
        permission.setLevel(dto.getLevel());

        return permission;
    }
    public PermissionDTO toEntity(Permission permission){
        if (permission == null){
            return null;
        }
        PermissionDTO dto = new PermissionDTO();
        dto.setCode(permission.getCode());
        dto.setId(permission.getCode());
        dto.setLevel(permission.getLevel());

        return dto;
    }

}
