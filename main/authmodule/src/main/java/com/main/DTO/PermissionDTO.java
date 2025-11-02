package com.main.DTO;
import jakarta.validation.constraints.NotBlank;

public class PermissionDTO {
    String id;
    @NotBlank(message = "Code is mandatory")
    String code;
    @NotBlank(message = "Level is mandatory")
    int level;
    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    
}
