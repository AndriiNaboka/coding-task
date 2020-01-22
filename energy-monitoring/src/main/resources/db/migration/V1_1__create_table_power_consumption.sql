CREATE TABLE power_consumption
(
    id          serial          NOT NULL,
    counter_id  integer         NOT NULL,
    amount      decimal         NOT NULL,
    created_at  timestamp       NOT NULL DEFAULT now(),
    CONSTRAINT power_consumption_pk PRIMARY KEY (id)
);
