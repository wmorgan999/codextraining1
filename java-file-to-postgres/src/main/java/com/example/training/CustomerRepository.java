package com.example.training;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository {
    private static final String UPSERT_SQL = """
            insert into customers (id, full_name, email, signup_date, account_balance)
            values (?, ?, ?, ?, ?)
            on conflict (id) do update set
                full_name = excluded.full_name,
                email = excluded.email,
                signup_date = excluded.signup_date,
                account_balance = excluded.account_balance
            """;

    public int saveAll(Connection connection, List<CustomerRecord> records) throws SQLException {
        int saved = 0;

        try (PreparedStatement statement = connection.prepareStatement(UPSERT_SQL)) {
            for (CustomerRecord record : records) {
                statement.setInt(1, record.id());
                statement.setString(2, record.fullName());
                statement.setString(3, record.email());
                statement.setDate(4, Date.valueOf(record.signupDate()));
                statement.setBigDecimal(5, record.accountBalance());
                statement.addBatch();
            }

            int[] counts = statement.executeBatch();
            for (int count : counts) {
                if (count >= 0) {
                    saved += count;
                }
            }
        }

        return saved;
    }
}

