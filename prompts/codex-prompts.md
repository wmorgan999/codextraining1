# Codex Prompt Examples

## Understand the Project

```text
Give me a high-level map of this Java project. Which classes parse files, validate rows, connect to Postgres, and run the import?
```

```text
Explain CustomerFileParser.java line by line, focusing on how it handles malformed input.
```

## Work in Small Steps

```text
Add one focused unit test for parsing a customer row with an invalid email. Keep the current project style.
```

```text
Implement the smallest change needed to skip blank lines in the input file. Add or update tests.
```

## Debugging

```text
I get this error when running mvn exec:java. Explain the likely cause and suggest checks in order from most likely to least likely:

<paste error here>
```

## Refactoring

```text
Review the importer code for readability. Suggest only low-risk refactors that keep behavior unchanged.
```

```text
Refactor duplicate validation logic, but do not change public method names or database behavior.
```

## Database Changes

```text
Add support for a new customer status field. Update the schema, parser, model, repository, and tests. Use ACTIVE as the default when the input field is blank.
```

## Review Before Running

```text
Review your proposed changes for possible regressions before I run the tests. Call out any assumptions.
```

