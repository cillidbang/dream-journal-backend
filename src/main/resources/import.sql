CREATE TABLE journalEntrySummary
(
    id       INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title    VARCHAR(255),
    subtitle VARCHAR(255),
    date     VARCHAR(255),
    content  TEXT
);





