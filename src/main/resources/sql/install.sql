CREATE TABLE public.accounts
(
  id INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(4096) NOT NULL,
  email VARCHAR(1024) NOT NULL,
  created TIMESTAMP NOT NULL,
  last_login TIMESTAMP,
  recovery_key VARCHAR(1024)
);