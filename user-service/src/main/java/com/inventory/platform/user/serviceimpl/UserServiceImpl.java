package com.inventory.platform.user.serviceimpl;

import com.inventory.platform.user.config.ApplicationConfig;
import com.inventory.platform.user.dto.UserRequest;
import com.inventory.platform.user.dto.UserResponse;
import com.inventory.platform.user.entity.Role;
import com.inventory.platform.user.entity.User;
import com.inventory.platform.user.exception.DuplicateResourceException;
import com.inventory.platform.user.exception.UserNotFoundException;
import com.inventory.platform.user.mapper.UserMapper;
import com.inventory.platform.user.repository.UserRepository;
import com.inventory.platform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log =
            LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserResponse createUser(UserRequest request) {
        log.info("Creating user with email : {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new DuplicateResourceException("Mobile already exists");
        }

        User user = UserMapper.toEntity(request);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id : {}", savedUser.getId());
        return UserMapper.toResponse(savedUser);

    }
    private User findUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id : " + id));
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("Fetching user with id : {}", id);
        User user=findUserById(id);
        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Get all users");
        List<User> users = userRepository.findAll();

        return UserMapper.toResponse(users);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user=findUserById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        User updatedUser=userRepository.save(user);
        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id : {}", id);
        User user = findUserById(id);

        userRepository.delete(user);
        log.info("Deleting user with id : {}", id);
    }
}