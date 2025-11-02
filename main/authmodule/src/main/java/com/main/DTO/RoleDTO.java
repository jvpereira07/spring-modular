package com.main.DTO;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
    private UUID id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Code is mandatory")
    private String code;
    @NotBlank(message = "Level is mandatory")
    private int level;
    private List<PermissionDTO> permissions;
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    private List<PermissionDTO> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
    
}
