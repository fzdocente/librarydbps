package com.example.librarydb.service;

import com.example.librarydb.dto.UserDTO;
import com.example.librarydb.dto.UserRequestDTO;
import com.example.librarydb.model.User;
import com.example.librarydb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID
    public UserDTO getUserById(String iduser) {

        User user = userRepository.findById(iduser)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado con el ID: " + iduser
                        )
                );

        return convertToDTO(user);
    }

    // Crear usuario
    /*public UserDTO createUser(User user) {

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }*/

    public UserDTO createUser(UserRequestDTO dto) {

        User user = new User();

        user.setIduser(dto.getIduser());
        user.setFullname(dto.getFullname());
        user.setSanctioned(dto.isSanctioned());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    // Actualizar usuario
    /*public UserDTO updateUser(String iduser, User userData) {

        User user = userRepository.findById(iduser)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado con el ID: " + iduser
                        )
                );

        user.setFullname(userData.getFullname());
        user.setSanctioned(userData.isSanctioned());

        // Solo actualiza el password si se proporciona uno
        if (userData.getPassword() != null &&
                !userData.getPassword().isBlank()) {

            user.setPassword(userData.getPassword());
        }

        User updatedUser = userRepository.save(user);

        return convertToDTO(updatedUser);
    }*/

    public UserDTO updateUser(String iduser, UserRequestDTO dto) {

        User user = userRepository.findById(iduser)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado con el ID: " + iduser
                        )
                );

        user.setFullname(dto.getFullname());
        user.setSanctioned(dto.isSanctioned());

        if (dto.getPassword() != null &&
                !dto.getPassword().isBlank()) {

            user.setPassword(dto.getPassword());
        }

        User updatedUser = userRepository.save(user);

        return convertToDTO(updatedUser);
    }

    // Eliminar usuario
    public void deleteUser(String iduser) {

        userRepository.deleteById(iduser);
    }

    // Convertir Entity a DTO
    private UserDTO convertToDTO(User user) {

        return new UserDTO(
                user.getIduser(),
                user.getFullname(),
                user.isSanctioned()
        );
    }
}