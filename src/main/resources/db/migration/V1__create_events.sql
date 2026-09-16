CREATE TABLE events
(
    id              UUID PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(255),
    location        VARCHAR(255) NOT NULL,
    start_date_time TIMESTAMP    NOT NULL,
    end_date_time   TIMESTAMP    NOT NULL,
    status          VARCHAR(30)  NOT NULL
)