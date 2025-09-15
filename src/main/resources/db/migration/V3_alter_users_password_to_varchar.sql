-- V3: make password column text-like, not numeric
ALTER TABLE users
ALTER COLUMN password TYPE VARCHAR(255);
