package com.example.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseResult {
    private final List<CustomerRecord> records = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();

    public void addRecord(CustomerRecord record) {
        records.add(record);
    }

    public void addError(String error) {
        errors.add(error);
    }

    public List<CustomerRecord> records() {
        return Collections.unmodifiableList(records);
    }

    public List<String> errors() {
        return Collections.unmodifiableList(errors);
    }
}

