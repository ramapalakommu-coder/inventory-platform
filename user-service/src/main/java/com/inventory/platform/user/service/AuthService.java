package com.inventory.platform.user.service;

import com.inventory.platform.user.dto.LoginRequest;
import com.inventory.platform.user.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
