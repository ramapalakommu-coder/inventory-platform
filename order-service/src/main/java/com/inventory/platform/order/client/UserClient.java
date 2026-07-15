package com.inventory.platform.order.client;

import com.inventory.platform.order.client.dto.UserResponse;
import com.inventory.platform.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE",configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/api/v1/users/{id}")
   UserResponse getUserById(@PathVariable Long id);

}
