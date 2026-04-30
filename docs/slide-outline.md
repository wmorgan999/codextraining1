# Slide Outline

## 1. Title

Using Codex with Eclipse to Build a Java File-to-Postgres Loader

## 2. What We Will Build

- Java command-line importer
- Pipe-delimited customer text file
- Validation and error reporting
- PostgreSQL `customers` table
- Tests and refactoring with Codex assistance

## 3. Workflow

1. Open project in Eclipse.
2. Use Eclipse Terminal to run Codex in the project folder.
3. Ask Codex focused questions.
4. Review generated changes.
5. Run tests and the importer.
6. Iterate.

## 4. Project Architecture

- `CustomerImportApp`: application entry point
- `CustomerFileParser`: text file parsing and validation
- `CustomerRecord`: immutable customer data
- `CustomerRepository`: JDBC persistence
- `DatabaseConfig`: environment-based database settings

## 5. Data Contract

Input file:

```text
id|full_name|email|signup_date|account_balance
```

Validation:

- Required fields cannot be blank.
- `id` must be a whole number.
- `email` must contain `@`.
- `signup_date` must use `yyyy-MM-dd`.
- `account_balance` must be numeric.

## 6. Database Contract

Table: `customers`

- `id` is the primary key.
- `email` is unique.
- Import uses upsert behavior.

## 7. Good Codex Prompts

- Ask for explanations before changes.
- Ask for one small change at a time.
- Ask Codex to add tests with code changes.
- Paste exact error messages when debugging.
- Ask for a review before running unfamiliar changes.

## 8. Hands-On Lab

- Run parser tests.
- Start Postgres.
- Run the importer.
- Query inserted rows.
- Add one validation rule.
- Use Codex to write or update tests.

## 9. Capstone

Add optional customer status:

- `ACTIVE`
- `INACTIVE`
- `PENDING`
- Blank defaults to `ACTIVE`

## 10. Debrief

- What did Codex do well?
- What needed human review?
- Which prompts produced the best results?
- What tests gave confidence?

