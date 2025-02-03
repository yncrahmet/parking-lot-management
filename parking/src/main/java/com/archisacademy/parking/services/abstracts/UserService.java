package com.archisacademy.parking.services.abstracts;

import com.archisacademy.parking.dtos.request.UserRequest;
import com.archisacademy.parking.dtos.request.UserUpdateRequest;
import com.archisacademy.parking.dtos.response.UserResponse;
import com.archisacademy.parking.dtos.response.UserUpdateResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserUpdateResponse updateUser(Long id , UserUpdateRequest userRequest);
    String  deleteUser(Long id);
}