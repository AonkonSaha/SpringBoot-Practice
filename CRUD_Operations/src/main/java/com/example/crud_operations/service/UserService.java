package com.example.crud_operations.service;

import com.example.crud_operations.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String saveUser(UserDTO userDTO);
    UserDTO fetchUserById(String id);
    String updateUserById(UserDTO userDTO,Long id);
    String deleteUserById(Long id);
    List<UserDTO> fetchAllUser();
}
