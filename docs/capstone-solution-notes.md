# Capstone Solution Notes

These notes are for the instructor. Do not hand them out until learners have attempted the challenge.

## Expected Design

Add a `status` field to:

- Input file
- `CustomerRecord`
- `CustomerFileParser`
- `CustomerRepository`
- `sql/schema.sql`
- Parser tests

## Suggested Schema Change

```sql
alter table customers
add column status text not null default 'ACTIVE';
```

For a fresh training database, the `create table` statement can include:

```sql
status text not null default 'ACTIVE'
```

## Parser Behavior

Accept either 5 or 6 columns during the transition:

- 5 columns: default status to `ACTIVE`
- 6 columns: validate the provided status

Valid values:

- `ACTIVE`
- `INACTIVE`
- `PENDING`

Unknown values should reject the row with a clear line-numbered error.

## Useful Codex Prompt

```text
Implement the capstone status field. Keep the existing parser style, add focused parser tests, update schema.sql, and update the JDBC upsert. Accept 5-column legacy rows by defaulting status to ACTIVE.
```

