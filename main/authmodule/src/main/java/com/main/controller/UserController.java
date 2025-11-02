package com.main.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.UserDTO;
import com.main.response.ApiResponse;
import com.main.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<UserDTO>>> getUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam Map<String, String> filters) {

        Page<UserDTO> users = userService.get(page, size, sortBy, sortDir, filters);
        ApiResponse<Page<UserDTO>> response = ApiResponse.ok(users, "Usuários recuperados com sucesso");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable UUID id) {
        UserDTO user = userService.getById(id);
        ApiResponse<UserDTO> response = ApiResponse.ok(user, "Usuário recuperado com sucesso");
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.create(userDTO);
        ApiResponse<UserDTO> response = ApiResponse.ok(createdUser, "Usuário criado com sucesso");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        ApiResponse<Void> response = ApiResponse.ok(null, "Usuário deletado com sucesso");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/teste")
  public String home() {
    return "Testes extremamente divertidos!";
  }

}
