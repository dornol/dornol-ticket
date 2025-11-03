ALTER TABLE manager
    ADD role INTEGER;

ALTER TABLE manager
    ALTER COLUMN role SET NOT NULL;

ALTER TABLE manager
    DROP COLUMN manager_role;