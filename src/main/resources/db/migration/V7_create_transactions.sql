-- Transactions linked to accounts (and the user who performed it)
create table if not exists transactions (
                                            id                varchar(255) primary key,
    account_id        varchar(255) not null,
    user_id           varchar(255) not null,
    type              varchar(20)  not null,         -- DEPOSIT | WITHDRAWAL | TRANSFER_IN | TRANSFER_OUT
    amount            numeric(19,2) not null check (amount > 0),
    currency          varchar(3)    not null,
    balance_after     numeric(19,2) not null,        -- account balance immediately after this txn
    description       varchar(255),
    created_at        timestamp with time zone default now(),

    constraint fk_tx_account foreign key (account_id) references accounts(id),
    constraint fk_tx_user    foreign key (user_id)    references users(id),
    constraint chk_tx_type   check (type in ('DEPOSIT','WITHDRAWAL','TRANSFER_IN','TRANSFER_OUT'))
    );

create index if not exists idx_tx_account_created_at on transactions(account_id, created_at desc);
