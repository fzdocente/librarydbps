package com.example.librarydb.controller;

import com.example.librarydb.dto.UserDTO;
import com.example.librarydb.model.User;
import com.example.librarydb.dto.UserRequestDTO;
import com.example.librarydb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    // GET /api/users/{iduser}
    // Obtener usuario por ID
    @GetMapping("/{iduser}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable String iduser) {

        return ResponseEntity.ok(
                userService.getUserById(iduser)
        );
    }

    // POST /api/users
    // Crear usuario
    /*@PostMapping
    public ResponseEntity<UserDTO> createUser(
            @Valid @RequestBody User user) {

        UserDTO createdUser = userService.createUser(user);

        return new ResponseEntity<>(
                createdUser,
                HttpStatus.CREATED
        );
    }*/
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @Valid @RequestBody UserRequestDTO dto) {

        UserDTO createdUser = userService.createUser(dto);

        return new ResponseEntity<>(
                createdUser,
                HttpStatus.CREATED
        );
    }

    // PUT /api/users/{iduser}
    // Actualizar usuario
    /*@PutMapping("/{iduser}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String iduser,
            @RequestBody User user) {

        return ResponseEntity.ok(
                userService.updateUser(iduser, user)
        );
    }

    */
    @PutMapping("/{iduser}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String iduser,
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(
                userService.updateUser(iduser, dto)
        );
    }

    // DELETE /api/users/{iduser}
    // Eliminar usuario
    @DeleteMapping("/{iduser}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable String iduser) {

        userService.deleteUser(iduser);

        return ResponseEntity.noContent().build();
    }
}