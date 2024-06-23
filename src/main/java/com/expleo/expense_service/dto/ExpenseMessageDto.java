package com.expleo.expense_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseMessageDto {

    private String userName;
    private String emailId;
    private String phoneNumber;
    private Double remainingBudget;
    private Double addBudget;
    private String expenseCategory;
    private String expenseDescription;
    private Double expenseAmount;
}
