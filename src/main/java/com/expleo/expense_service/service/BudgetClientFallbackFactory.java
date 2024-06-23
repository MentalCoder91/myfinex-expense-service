package com.expleo.expense_service.service;

import com.expleo.expense_service.dto.Budget;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class BudgetClientFallbackFactory implements FallbackFactory<BudgetClient> {
    @Override
    public BudgetClient create(Throwable cause) {
        return new BudgetClient() {
            @Override
            public Budget getBudgetsByUserIdAndCategory(Long userId, Long categoryId) {

                return new Budget(999L, 999L, 0.0, null, cause.getMessage());

            }


        };
    }
}