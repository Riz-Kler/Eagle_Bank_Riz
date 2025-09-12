-- Users
CREATE TABLE users (
                       id           BIGSERIAL PRIMARY KEY,
                       full_name    VARCHAR(160) NOT NULL,
                       email        VARCHAR(160) NOT NULL UNIQUE,
                       password_hash VARCHAR(100) NOT NULL,
                       created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Accounts
CREATE TABLE accounts (
                          id           BIGSERIAL PRIMARY KEY,
                          user_id      BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          sort_code    VARCHAR(8) NOT NULL,       -- e.g. 11-11-11
                          account_no   VARCHAR(20) NOT NULL UNIQUE,
                          currency     CHAR(3) NOT NULL DEFAULT 'GBP',
                          balance_minor BIGINT NOT NULL DEFAULT 0, -- pence
                          created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Transactions
CREATE TABLE transactions (
                              id            BIGSERIAL PRIMARY KEY,
                              account_id    BIGINT NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
                              type          VARCHAR(20) NOT NULL,      -- deposit | withdrawal
                              amount_minor  BIGINT NOT NULL,           -- pence
                              created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
                              CHECK (amount_minor > 0)
);

CREATE INDEX idx_accounts_user ON accounts(user_id);
CREATE INDEX idx_tx_account ON transactions(account_id);
