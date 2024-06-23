package com.expleo.expense_service.repository;

import com.expleo.expense_service.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserIdAndCategoryId(Long userId, Long categoryId);


    List<Expense> findByUserId(Long userId);
}