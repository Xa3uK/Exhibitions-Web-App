CREATE TABLE IF NOT EXISTS tickets
(
    id serial PRIMARY KEY,
    user_id integer NOT NULL,
    exhibition_id integer NOT NULL,
    FOREIGN KEY (exhibition_id)
        REFERENCES exhibitions (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES public.users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)