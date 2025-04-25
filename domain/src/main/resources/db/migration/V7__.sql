ALTER TABLE performance_site
    ADD performance_date date;

ALTER TABLE performance_site
    ADD performance_time time WITHOUT TIME ZONE;

ALTER TABLE performance_site
    ALTER COLUMN performance_date SET NOT NULL;

ALTER TABLE performance_site
    ALTER COLUMN performance_time SET NOT NULL;