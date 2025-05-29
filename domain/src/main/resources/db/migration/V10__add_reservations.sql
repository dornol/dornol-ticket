ALTER TABLE performance_schedule_seat_group_price
    DROP CONSTRAINT fk_performance_schedule_seat_group_price_on_seat_group;

ALTER TABLE performance_schedule_seat_group_price
    DROP CONSTRAINT fk_performancescheduleseatgroupprice_on_performanceschedule;

CREATE TABLE performance_schedule_seat_group
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
    CONSTRAINT pk_performance_schedule_seat_group PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id                     BIGINT      NOT NULL,
    deleted_by             BIGINT,
    deleted_date           TIMESTAMP WITHOUT TIME ZONE,
    deleted                BOOLEAN     NOT NULL,
    created_by             BIGINT,
    last_modified_by       BIGINT,
    created_date           TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date     TIMESTAMP WITHOUT TIME ZONE,
    price                  DECIMAL,
    state                  VARCHAR(20) NOT NULL,
    cancel_reason          VARCHAR(20),
    schedule_seat_group_id BIGINT      NOT NULL,
    seat_id                BIGINT      NOT NULL,
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);

CREATE TABLE reservation_payment
(
    id                 BIGINT      NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN     NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    price              DECIMAL,
    payment_state      VARCHAR(20) NOT NULL,
    reservation_id     BIGINT      NOT NULL,
    CONSTRAINT pk_reservation_payment PRIMARY KEY (id)
);

ALTER TABLE performance_schedule_seat_group
    ADD CONSTRAINT FK_PERFORMANCE_SCHEDULE_SEAT_GROUP_ON_PERFORMANCE_SCHEDULE FOREIGN KEY (performance_schedule_id) REFERENCES performance_schedule (id);

ALTER TABLE performance_schedule_seat_group
    ADD CONSTRAINT FK_PERFORMANCE_SCHEDULE_SEAT_GROUP_ON_SEAT_GROUP FOREIGN KEY (seat_group_id) REFERENCES seat_group (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_SCHEDULE_SEAT_GROUP FOREIGN KEY (schedule_seat_group_id) REFERENCES performance_schedule_seat_group (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seat (id);

ALTER TABLE reservation_payment
    ADD CONSTRAINT FK_RESERVATION_PAYMENT_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservation (id);

DROP TABLE performance_schedule_seat_group_price CASCADE;