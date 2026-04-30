package com.example.training;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomerFileParser {
    private static final int EXPECTED_COLUMNS = 5;

    public ParseResult parse(Path inputFile) throws IOException {
        ParseResult result = new ParseResult();
        int lineNumber = 0;

        for (String line : Files.readAllLines(inputFile)) {
            lineNumber++;

            if (lineNumber == 1 || line.isBlank()) {
                continue;
            }

            parseLine(line, lineNumber, result);
        }

        return result;
    }

    private void parseLine(String line, int lineNumber, ParseResult result) {
        String[] columns = line.split("\\|", -1);

        if (columns.length != EXPECTED_COLUMNS) {
            result.addError("Line " + lineNumber + ": expected 5 columns but found " + columns.length);
            return;
        }

        try {
            int id = parseId(columns[0], lineNumber);
            String fullName = requireText(columns[1], "full_name", lineNumber);
            String email = parseEmail(columns[2], lineNumber);
            LocalDate signupDate = parseDate(columns[3], lineNumber);
            BigDecimal accountBalance = parseBalance(columns[4], lineNumber);

            result.addRecord(new CustomerRecord(id, fullName, email, signupDate, accountBalance));
        } catch (IllegalArgumentException ex) {
            result.addError(ex.getMessage());
        }
    }

    private int parseId(String value, int lineNumber) {
        try {
            return Integer.parseInt(requireText(value, "id", lineNumber));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Line " + lineNumber + ": id must be a whole number");
        }
    }

    private String parseEmail(String value, int lineNumber) {
        String email = requireText(value, "email", lineNumber);
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Line " + lineNumber + ": email must contain @");
        }
        return email;
    }

    private LocalDate parseDate(String value, int lineNumber) {
        try {
            return LocalDate.parse(requireText(value, "signup_date", lineNumber));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Line " + lineNumber + ": signup_date must use yyyy-MM-dd");
        }
    }

    private BigDecimal parseBalance(String value, int lineNumber) {
        try {
            return new BigDecimal(requireText(value, "account_balance", lineNumber));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Line " + lineNumber + ": account_balance must be numeric");
        }
    }

    private String requireText(String value, String fieldName, int lineNumber) {
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Line " + lineNumber + ": " + fieldName + " is required");
        }
        return trimmed;
    }
}

