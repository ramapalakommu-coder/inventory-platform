package com.inventory.platform.user.mapper;

import com.inventory.platform.user.dto.UserRequest;
import com.inventory.platform.user.dto.UserResponse;
import com.inventory.platform.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequest request) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .build();
    }
    public static List<UserResponse> toResponse(List<User> users) {

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            responses.add(toResponse(user));
        }

        return responses;
    }

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .createdAt(user.getCreatedAt())
                .build();
    }
}