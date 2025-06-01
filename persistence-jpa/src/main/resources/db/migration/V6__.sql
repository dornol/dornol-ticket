ALTER TABLE seat_group
    ADD color VARCHAR(7);

ALTER TABLE seat_group
    ALTER COLUMN color SET NOT NULL;