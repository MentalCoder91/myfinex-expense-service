package com.expleo.expense_service.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record User(Long id, String userName, String emailId, String phoneNumber) {}
