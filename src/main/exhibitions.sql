CREATE TABLE IF NOT EXISTS exhibitions
(
    id serial PRIMARY KEY,
    theme character varying(255) NOT NULL,
    hall character varying(255) NOT NULL,
    start_date timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    price numeric NOT NULL
)
