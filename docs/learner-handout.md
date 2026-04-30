# Learner Handout

## Objective

You will use Codex inside an Eclipse workflow to understand and improve a Java program that reads customer records from a text file and loads valid records into PostgreSQL.

## Part 1: Import the Project

1. Open Eclipse.
2. Select `File > Import`.
3. Choose `Maven > Existing Maven Projects`.
4. Browse to `java-file-to-postgres`.
5. Finish the import.

## Part 2: Open Codex in Eclipse

1. Open `Window > Show View > Terminal`.
2. Start a local terminal in the `java-file-to-postgres` folder.
3. Run:

```sh
codex
```

4. Try this prompt:

```text
Explain this project like I am a Java developer who knows basic classes but has not used JDBC before.
```

## Part 3: Review the Data File

Open `data/customers.txt`.

The file is pipe-delimited:

```text
id|full_name|email|signup_date|account_balance
```

Each valid row should contain:

- Numeric customer id
- Full name
- Email address
- Signup date in `yyyy-MM-dd` format
- Decimal account balance

## Part 4: Prepare PostgreSQL

Option A, Docker:

```sh
docker compose up -d
```

Option B, local PostgreSQL:

Create a database named `trainingdb`.

Run the schema:

```sh
psql -h localhost -U training -d trainingdb -f sql/schema.sql
```

Default connection settings used by the app:

- URL: `jdbc:postgresql://localhost:5432/trainingdb`
- User: `training`
- Password: `training`

## Part 5: Run Tests

From the terminal:

```sh
mvn test
```

If Maven is not available in your terminal, run tests from Eclipse:

1. Right-click the project.
2. Select `Run As > Maven test`.

## Part 6: Run the Importer

From the terminal:

```sh
mvn exec:java
```

Or in Eclipse:

1. Open `CustomerImportApp.java`.
2. Select `Run As > Java Application`.
3. If needed, add environment variables:
   - `DB_URL`
   - `DB_USER`
   - `DB_PASSWORD`
   - `INPUT_FILE`

## Part 7: Ask Codex for Help

Use Codex for focused tasks:

```text
Find the class that parses customers.txt and explain how invalid rows are handled.
```

```text
Add a parser test for a row with a bad signup date.
```

```text
Explain what this JDBC error means and suggest the smallest fix.
```

```text
Refactor the importer so the database connection settings are easier to configure, but keep the behavior the same.
```

## Capstone

Add an optional `status` column.

Rules:

- Valid values: `ACTIVE`, `INACTIVE`, `PENDING`
- Blank status defaults to `ACTIVE`
- Invalid status rejects the row
- Database stores the final status value

Use Codex to help, but review every change before running it.

