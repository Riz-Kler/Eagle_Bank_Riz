create table if not exists accounts (
                                        id varchar(255) primary key,
    user_id varchar(40) not null,
    account_type varchar(40) not null,
    currency varchar(3) not null,
    balance numeric(19,2) not null default 0,
    created_at timestamp with time zone,
    updated_at timestamp with time zone
                             );
