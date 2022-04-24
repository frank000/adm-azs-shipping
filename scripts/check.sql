\-- Get a list of databases
SELECT datname FROM pg_database
WHERE datistemplate = false

\c cliente
\d frete