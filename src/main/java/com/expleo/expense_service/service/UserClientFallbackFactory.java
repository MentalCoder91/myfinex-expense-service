package com.expleo.expense_service.service;

import com.expleo.expense_service.dto.Budget;
import com.expleo.expense_service.dto.User;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public User getUserFromUserService(Long userId) {
                return new User(-1L,cause.getMessage(),"fallback@MyyFinEx","9999999999");
            }



        };
    }
}