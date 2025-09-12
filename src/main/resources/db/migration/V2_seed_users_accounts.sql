-- BCrypt hashes for: 11111, 22222, 33333, 44444, 55555
-- Use a single cost 10 hash per value.
-- (You can generate with Spring Security's BCryptPasswordEncoder or online tools.)

INSERT INTO users (full_name, email, password_hash) VALUES
                                                        ('Riz Kler',   'RizKler@test.com',   '$2a$10$z4o7p3H3pYf9vXrR5q8mmeX8EXAMPLE11111'),
                                                        ('Alice Smith','AliceSmith@test.com','$2a$10$z4o7p3H3pYf9vXrR5q8mmeX8EXAMPLE22222'),
                                                        ('Joe Bloggs', 'JoeBloggs@test.com', '$2a$10$z4o7p3H3pYf9vXrR5q8mmeX8EXAMPLE33333'),
                                                        ('Eve Jones',  'EveJones@test.com',  '$2a$10$z4o7p3H3pYf9vXrR5q8mmeX8EXAMPLE44444'),
                                                        ('Raj Patel',  'RajPatel@test.com',  '$2a$10$z4o7p3H3pYf9vXrR5q8mmeX8EXAMPLE55555');

-- Accounts with starting £500.00 = 50000 pence
WITH u AS (SELECT id, email FROM users)
INSERT INTO accounts (user_id, sort_code, account_no, currency, balance_minor)
VALUES
((SELECT id FROM u WHERE email='RizKler@test.com'),   '11-11-11', '11111', 'GBP', 50000),
((SELECT id FROM u WHERE email='AliceSmith@test.com'),'11-11-11', '22222', 'GBP', 50000),
((SELECT id FROM u WHERE email='JoeBloggs@test.com'), '11-11-11', '33333', 'GBP', 50000),
((SELECT id FROM u WHERE email='EveJones@test.com'),  '11-11-11', '44444', 'GBP', 50000),
((SELECT id FROM u WHERE email='RajPatel@test.com'),  '11-11-11', '55555', 'GBP', 50000);
