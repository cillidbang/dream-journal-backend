/* import tables */
CREATE TABLE journalEntrySummary
(
    id  SERIAL PRIMARY KEY,
    title    VARCHAR(255),
    subtitle VARCHAR(255),
    date     VARCHAR(255),
    content  TEXT
);


CREATE TABLE Images
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    file_name varchar(255) NOT NULL,
    file_path varchar(255) NOT NULL,
    journal_id INT REFERENCES journalEntrySummary (id)
);










