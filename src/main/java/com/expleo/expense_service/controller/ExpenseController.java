package com.expleo.expense_service.controller;


import com.expleo.expense_service.entity.Expense;
import com.expleo.expense_service.logging.LogExecutionTime;
import com.expleo.expense_service.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/create")
    @LogExecutionTime
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }


    @GetMapping("/{userId}")
    @LogExecutionTime
    public ResponseEntity<List<Expense>> getUserExpenses(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(expenseService.getUserExpenses(userId));
    }






}
