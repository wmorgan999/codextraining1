package com.example.training;

public record DatabaseConfig(String url, String user, String password) {
    public static DatabaseConfig fromEnvironment() {
        return new DatabaseConfig(
                readEnv("DB_URL", "jdbc:postgresql://localhost:5432/trainingdb"),
                readEnv("DB_USER", "training"),
                readEnv("DB_PASSWORD", "training")
        );
    }

    private static String readEnv(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value;
    }
}

