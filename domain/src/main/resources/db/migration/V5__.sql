ALTER TABLE seat
    ADD display_order BIGINT;

ALTER TABLE seat
    ADD x DOUBLE PRECISION;

ALTER TABLE seat
    ADD y DOUBLE PRECISION;

ALTER TABLE seat
    ALTER COLUMN display_order SET NOT NULL;

ALTER TABLE seat_group
    ADD display_order BIGINT;

ALTER TABLE seat_group
    ALTER COLUMN display_order SET NOT NULL;

ALTER TABLE seat
    ALTER COLUMN x SET NOT NULL;

ALTER TABLE seat
    ALTER COLUMN y SET NOT NULL;

