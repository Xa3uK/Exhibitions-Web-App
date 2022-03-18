CREATE TABLE IF NOT EXISTS users
(
    id serial PRIMARY KEY,
    login character varying(255) UNIQUE NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) UNIQUE NOT NULL,
    role character varying(255) NOT NULL,
    money numeric NOT NULL DEFAULT 0
)
