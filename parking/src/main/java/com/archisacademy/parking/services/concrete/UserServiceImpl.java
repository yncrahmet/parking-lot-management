package com.archisacademy.parking.services.concrete;

import com.archisacademy.parking.dtos.request.UserRequest;
import com.archisacademy.parking.dtos.request.UserUpdateRequest;
import com.archisacademy.parking.dtos.response.UserResponse;
import com.archisacademy.parking.dtos.response.UserUpdateResponse;
import com.archisacademy.parking.exception.user.UserExistByEmailException;
import com.archisacademy.parking.exception.user.UserNotFoundException;
import com.archisacademy.parking.model.User;
import com.archisacademy.parking.modelmapper.ModelMapperServiceImpl;
import com.archisacademy.parking.repositories.UserRepository;
import com.archisacademy.parking.services.abstracts.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperServiceImpl modelMapperServiceImpl;

    public UserServiceImpl(UserRepository userRepository, ModelMapperServiceImpl modelMapperServiceImpl) {
        this.userRepository = userRepository;
        this.modelMapperServiceImpl=modelMapperServiceImpl;

    }
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
           new UserExistByEmailException("Email already exists");
        }
        User user =modelMapperServiceImpl.request().map(userRequest, User.class);
        user.setActive(true);
        userRepository.save(user);
        UserResponse userResponse = modelMapperServiceImpl.response().map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses=users.stream().map(user ->modelMapperServiceImpl.response().map(user,UserResponse.class))
                .collect(Collectors.toList());
        return userResponses;
    }

    @Transactional
    @Override
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        modelMapperServiceImpl.request().map(userRequest, user);
        userRepository.save(user);

        return new UserUpdateResponse("User successfully updated");
    }

    @Transactional
    @Override
    public String deleteUser(Long id) {
        Optional<User> user =userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
        return "User successfully deleted";
    }

}
