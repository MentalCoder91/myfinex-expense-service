package com.expleo.expense_service.service;

import com.expleo.expense_service.config.AppConfig;
import com.expleo.expense_service.dto.Budget;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "BUDGET-SERVICE", configuration = AppConfig.class,fallbackFactory = BudgetClientFallbackFactory.class)
public interface BudgetClient {

    @GetMapping("/budget/user/{userId}/category/{categoryId}")
    Budget getBudgetsByUserIdAndCategory(@RequestParam("userId") Long userId,
                                               @RequestParam("categoryId") Long categoryId);
}

