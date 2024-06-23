package com.expleo.expense_service.service;


import com.expleo.expense_service.constants.Constants;
import com.expleo.expense_service.dto.Budget;
import com.expleo.expense_service.dto.ExpenseMessageDto;
import com.expleo.expense_service.dto.User;
import com.expleo.expense_service.entity.Expense;
import com.expleo.expense_service.exception.*;
import com.expleo.expense_service.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BudgetClient budgetClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private StreamBridge streamBridge;

    @Transactional(propagation = Propagation.REQUIRED)
    public Expense createExpense(Expense expense) {

        //Calling Budget MS
        Budget budgets = budgetClient.getBudgetsByUserIdAndCategory(expense.getUserId(), expense.getCategoryId());

        if (budgets == null) {
            throw new BudgetNotFoundException("Budget not found for the specified category and user");
        }

        if(budgets.getMessage()!=null){
            throw new ServiceUnavailableException("The budget service is not responding. Please try again later..."+budgets.getMessage());
        }
        //Calling User MS
        User user = userClient.getUserFromUserService(expense.getUserId());

        if (user == null) {
            throw new UserNotFoundException("User not found!!");
        }

        if(user.emailId().equalsIgnoreCase(Constants.FALLBACK)){
            throw new ServiceUnavailableException("The User service is not responding. Please try again later..."+user.userName());
        }



        // Calculate total expenses for the category
        double totalExpenses = expenseRepository.findByUserIdAndCategoryId(expense.getUserId(), expense.getCategoryId())
                .stream().mapToDouble(Expense::getAmount).sum();

        // Calculate total budget
        double totalBudget = budgets.getAmount();

        // Check if the new expense would exceed the total budget
        if (totalExpenses + expense.getAmount() > totalBudget) {
            //Sending async message to Notification MS
            streamBridge.send("sendCommunication-out-0", processExpenseDetails(
                    user, totalExpenses, totalBudget, expense, budgets));
            throw new RuntimeException("Budget exceeded for the expense :" + expense.getDescription());
        }

        // Save the new expense
        return expenseRepository.save(expense);
    }

    private ExpenseMessageDto processExpenseDetails(User user, double totalExpenses, double totalBudget,
                                                    Expense expense, Budget budgets) {

        ExpenseMessageDto dto = new ExpenseMessageDto();
        Double remainingBudget = totalBudget - totalExpenses;
        dto.setUserName(user.userName());
        dto.setEmailId(user.emailId());
        dto.setRemainingBudget(remainingBudget);
        dto.setPhoneNumber(user.phoneNumber());
        dto.setExpenseCategory(budgets.getCategory().getName());
        dto.setExpenseDescription(expense.getDescription());
        dto.setExpenseAmount(expense.getAmount());
        return dto;

    }


    public List<Expense> getUserExpenses(Long userId) {

        List<Expense> byUserId = expenseRepository.findByUserId(userId);
        if(byUserId.isEmpty()){
            throw new ExpenseNotFoundException("Expense not found for the user");
        }
        return byUserId;
    }





}
