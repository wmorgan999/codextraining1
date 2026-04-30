# Instructor Guide

## Session Goal

Demonstrate a realistic developer workflow where Codex helps build, explain, test, and improve a Java application in Eclipse. The application reads customer data from a text file and loads valid rows into PostgreSQL.

## Prerequisites

Instructor machine:

- Eclipse IDE installed
- Java 17 or newer
- PostgreSQL or Docker
- Maven available from terminal, or Eclipse Maven support
- Codex available from a terminal

Learner machines should match the same setup where possible.

## Setup Before Class

1. Open Eclipse.
2. Import `java-file-to-postgres`:
   - `File > Import > Maven > Existing Maven Projects`
   - Select the `java-file-to-postgres` folder.
3. Start Postgres:
   - With Docker: `docker compose up -d`
   - Or create a local database named `trainingdb`.
4. Run `sql/schema.sql` against the database.
5. Open Eclipse's Terminal view:
   - `Window > Show View > Terminal`
   - Start a local terminal in the project folder.
6. Confirm Codex runs:
   - `codex`

## Module Plan

### Module 1: Eclipse and Codex Workflow

Time: 30 minutes

Demonstrate:

- Opening Codex in Eclipse's Terminal view.
- Giving Codex local project context.
- Asking Codex to explain a class.
- Asking Codex to generate a test.
- Reviewing AI suggestions before applying changes.

Key message: Codex works best as a coding partner. The developer remains responsible for intent, review, and verification.

Suggested demo prompt:

```text
Explain the purpose of this Java Maven project and identify the main classes involved in reading a text file and loading PostgreSQL.
```

### Module 2: Java Project Walkthrough

Time: 30 minutes

Walk through:

- `CustomerRecord`
- `CustomerFileParser`
- `CustomerRepository`
- `CustomerImportApp`
- `data/customers.txt`
- `sql/schema.sql`

Discuss separation of concerns:

- Parsing text
- Validating data
- Database persistence
- Application orchestration

### Module 3: Text File Parsing and Validation

Time: 45 minutes

Lab:

- Inspect `data/customers.txt`.
- Add an invalid email row.
- Run the parser tests.
- Ask Codex to explain why invalid rows are rejected.

Discussion:

- Header rows
- Delimiters
- Required fields
- Bad input handling
- Error reporting

### Module 4: PostgreSQL Setup and Schema

Time: 30 minutes

Demonstrate:

- Starting Postgres.
- Creating the `customers` table.
- Connecting with `psql` or an Eclipse database view.

Schema focus:

- Primary keys
- Unique constraints
- Data types
- Insert behavior

### Module 5: JDBC Loading Lab

Time: 60 minutes

Learners run the importer:

```sh
mvn test
mvn exec:java
```

Then verify:

```sql
select id, full_name, email, signup_date, account_balance
from customers
order by id;
```

Codex tasks:

- Ask Codex to add better logging.
- Ask Codex to make database settings configurable.
- Ask Codex to explain a JDBC exception.

### Module 6: Codex-Assisted Testing and Refactoring

Time: 45 minutes

Prompt learners to ask Codex for:

- Additional parser tests.
- A repository integration test idea.
- Refactoring suggestions.

Review:

- What suggestions are safe?
- What needs human judgment?
- What needs tests?

### Module 7: Capstone Challenge

Time: 60 minutes

Challenge:

Add support for an optional `status` field in the input file and database.

Requirements:

- Accept `ACTIVE`, `INACTIVE`, or `PENDING`.
- Default blank status to `ACTIVE`.
- Reject unknown statuses.
- Store status in PostgreSQL.
- Add parser tests.

Assessment:

- Application still imports valid rows.
- Invalid rows are skipped with useful error output.
- Tests cover at least one valid and one invalid status case.
- Learner can explain where Codex helped and where they made the final design decision.

