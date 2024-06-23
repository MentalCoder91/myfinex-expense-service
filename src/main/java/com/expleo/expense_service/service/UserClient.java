package com.expleo.expense_service.service;

import com.expleo.expense_service.config.AppConfig;
import com.expleo.expense_service.dto.Budget;
import com.expleo.expense_service.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "USER-SERVICE",configuration = AppConfig.class,fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @GetMapping("/users/find/{userId}")
    User getUserFromUserService(@RequestParam("userId") Long userId);
}