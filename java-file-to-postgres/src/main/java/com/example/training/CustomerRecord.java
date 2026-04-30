package com.example.training;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CustomerRecord(
        int id,
        String fullName,
        String email,
        LocalDate signupDate,
        BigDecimal accountBalance
) {
}

