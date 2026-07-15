package com.inventory.platform.user.controller;

import com.inventory.platform.user.dto.UserRequest;
import com.inventory.platform.user.dto.UserResponse;
import com.inventory.platform.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello from user service";
    }
    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {

        return userService.createUser(request);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public UserResponse getUser(@PathVariable Long id) {

        return userService.getUserById(id);
    }
    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('USER')")
public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
}

@PutMapping("/updateUser/{id}")
@PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request){

        return  userService.updateUser(id,request);
    }
    @DeleteMapping ("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }


}
