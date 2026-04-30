package com.example.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CustomerFileParserTest {
    @TempDir
    Path tempDir;

    @Test
    void parsesValidCustomerRows() throws Exception {
        Path file = writeInput("""
                id|full_name|email|signup_date|account_balance
                1001|Ada Lovelace|ada@example.com|2025-01-15|125.50
                1002|Grace Hopper|grace@example.com|2025-02-20|300.00
                """);

        ParseResult result = new CustomerFileParser().parse(file);

        assertEquals(2, result.records().size());
        assertEquals(0, result.errors().size());
        assertEquals("Ada Lovelace", result.records().get(0).fullName());
    }

    @Test
    void reportsInvalidRowsAndContinuesParsing() throws Exception {
        Path file = writeInput("""
                id|full_name|email|signup_date|account_balance
                bad-id|Invalid Person|invalid@example.com|2025-03-10|42.00
                1004|No Email||2025-03-11|10.00
                1005|Valid Person|valid@example.com|2025-03-12|20.00
                """);

        ParseResult result = new CustomerFileParser().parse(file);

        assertEquals(1, result.records().size());
        assertEquals(2, result.errors().size());
        assertTrue(result.errors().get(0).contains("id must be a whole number"));
        assertTrue(result.errors().get(1).contains("email is required"));
    }

    @Test
    void reportsBadDateFormat() throws Exception {
        Path file = writeInput("""
                id|full_name|email|signup_date|account_balance
                1001|Ada Lovelace|ada@example.com|01/15/2025|125.50
                """);

        ParseResult result = new CustomerFileParser().parse(file);

        assertEquals(0, result.records().size());
        assertEquals(1, result.errors().size());
        assertTrue(result.errors().get(0).contains("signup_date must use yyyy-MM-dd"));
    }

    private Path writeInput(String content) throws Exception {
        Path file = tempDir.resolve("customers.txt");
        Files.writeString(file, content);
        return file;
    }
}

