CREATE TABLE performance_schedule_seat_group_price
(
    id                      BIGINT  NOT NULL,
    deleted_by              BIGINT,
    deleted_date            TIMESTAMP WITHOUT TIME ZONE,
    deleted                 BOOLEAN NOT NULL,
    created_by              BIGINT,
    last_modified_by        BIGINT,
    created_date            TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date      TIMESTAMP WITHOUT TIME ZONE,
    price                   DECIMAL NOT NULL,
    performance_schedule_id BIGINT  NOT NULL,
    seat_group_id           BIGINT  NOT NULL,
    CONSTRAINT pk_performance_schedule_seat_group_price PRIMARY KEY (id)
);

ALTER TABLE performance_schedule_seat_group_price
    ADD CONSTRAINT FK_PERFORMANCESCHEDULESEATGROUPPRICE_ON_PERFORMANCESCHEDULE FOREIGN KEY (performance_schedule_id) REFERENCES performance_schedule (id);

ALTER TABLE performance_schedule_seat_group_price
    ADD CONSTRAINT FK_PERFORMANCE_SCHEDULE_SEAT_GROUP_PRICE_ON_SEAT_GROUP FOREIGN KEY (seat_group_id) REFERENCES seat_group (id);

ALTER TABLE site
    DROP CONSTRAINT uc_site_seating_map_file;