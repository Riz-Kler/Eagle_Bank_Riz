-- V6_add_account_identifiers.sql
ALTER TABLE accounts ADD COLUMN IF NOT EXISTS account_number varchar(16);
ALTER TABLE accounts ADD COLUMN IF NOT EXISTS sort_code varchar(8);
CREATE UNIQUE INDEX IF NOT EXISTS ux_accounts_account_number ON accounts(account_number);
