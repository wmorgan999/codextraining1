package com.example.training;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

public class CustomerImportApp {
    public static void main(String[] args) throws Exception {
        Path inputFile = Path.of(readEnv("INPUT_FILE", "data/customers.txt"));
        DatabaseConfig databaseConfig = DatabaseConfig.fromEnvironment();

        CustomerFileParser parser = new CustomerFileParser();
        ParseResult parseResult = parser.parse(inputFile);

        for (String error : parseResult.errors()) {
            System.err.println(error);
        }

        try (Connection connection = DriverManager.getConnection(
                databaseConfig.url(),
                databaseConfig.user(),
                databaseConfig.password())) {
            CustomerRepository repository = new CustomerRepository();
            int saved = repository.saveAll(connection, parseResult.records());

            System.out.println("Valid records: " + parseResult.records().size());
            System.out.println("Invalid rows: " + parseResult.errors().size());
            System.out.println("Database rows saved: " + saved);
        }
    }

    private static String readEnv(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value;
    }
}

