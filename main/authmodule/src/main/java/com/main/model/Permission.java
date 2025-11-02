package com.main.model;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Permissions")
public class Permission {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private int level; // if one role have a level higher than this level, then it has this permission
    @ManyToMany(mappedBy = "permissions")
    @Column(nullable = false)
    private List<Role> roles;
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
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    // Constructors
    public Permission() {
    }
    public Permission(String code, String description, int level, List<Role> roles) {
        this.code = code;
        this.level = level;
        this.roles = roles;
        
    }
}
