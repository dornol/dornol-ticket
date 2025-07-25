ALTER TABLE reservation_payment
    ADD payment_status INTEGER;

ALTER TABLE reservation_payment
    ALTER COLUMN payment_status SET NOT NULL;

ALTER TABLE reservation_payment
    DROP COLUMN payment_state;

ALTER TABLE reservation
    DROP COLUMN cancel_reason;

ALTER TABLE reservation
    DROP COLUMN state;

ALTER TABLE reservation
    ADD cancel_reason INTEGER NOT NULL;

ALTER TABLE reservation
    ALTER COLUMN cancel_reason SET NOT NULL;

ALTER TABLE manager
    DROP COLUMN manager_role;

ALTER TABLE manager
    ADD manager_role INTEGER NOT NULL;

ALTER TABLE reservation
    ADD state INTEGER NOT NULL;

ALTER TABLE performance
    DROP COLUMN type;

ALTER TABLE performance
    ADD type INTEGER NOT NULL;

ALTER TABLE performance
    ALTER COLUMN type SET NOT NULL;