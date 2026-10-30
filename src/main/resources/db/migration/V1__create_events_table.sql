CREATE TABLE events
(
    id           UUID PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    location     VARCHAR(255) NOT NULL,
    start_time   TIMESTAMP    NOT NULL,
    end_time     TIMESTAMP    NOT NULL,
    event_status VARCHAR(50)  NOT NULL
)