-- SQL for todoliste-eksemplet gjennomgått i timen onsdag 26. mars 2025 

DROP SCHEMA IF EXISTS forelesning5_todoliste CASCADE;
CREATE SCHEMA forelesning5_todoliste;
SET search_path TO forelesning5_todoliste;

CREATE TABLE todoliste
(
    id		SERIAL PRIMARY KEY,
    navn	VARCHAR
);

CREATE TABLE todo
(
    id      SERIAL PRIMARY KEY,
    tekst   VARCHAR,
    listeid INTEGER,
    CONSTRAINT listeFK FOREIGN KEY (listeid) REFERENCES todoliste(id)
);

-- SELECT * FROM todoliste ORDER BY id ASC;
-- SELECT * FROM todo ORDER BY id ASC;
