CREATE TABLE performance
(
    id                 BIGINT       NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255) NOT NULL,
    type               VARCHAR(255),
    site_id            BIGINT       NOT NULL,
    CONSTRAINT pk_performance PRIMARY KEY (id)
);

CREATE TABLE performance_site
(
    id                 BIGINT  NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    performance_id     BIGINT  NOT NULL,
    site_id            BIGINT  NOT NULL,
    CONSTRAINT pk_performancesite PRIMARY KEY (id)
);

CREATE TABLE seat
(
    id                 BIGINT      NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN     NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(30) NOT NULL,
    seat_group_id      BIGINT      NOT NULL,
    CONSTRAINT pk_seat PRIMARY KEY (id)
);

CREATE TABLE seat_group
(
    id                 BIGINT      NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN     NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(30) NOT NULL,
    site_id            BIGINT      NOT NULL,
    CONSTRAINT pk_seatgroup PRIMARY KEY (id)
);

CREATE TABLE site
(
    id                 BIGINT       NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(100) NOT NULL,
    company_id         BIGINT       NOT NULL,
    zip_code           VARCHAR(5),
    main_address       VARCHAR(255),
    detail_address     VARCHAR(255),
    CONSTRAINT pk_site PRIMARY KEY (id)
);

CREATE INDEX idx_performance_site_domain ON performance_site (performance_id, site_id);

ALTER TABLE performance_site
    ADD CONSTRAINT FK_PERFORMANCESITE_ON_PERFORMANCE FOREIGN KEY (performance_id) REFERENCES performance (id);

ALTER TABLE performance_site
    ADD CONSTRAINT FK_PERFORMANCESITE_ON_SITE FOREIGN KEY (site_id) REFERENCES site (id);

ALTER TABLE performance
    ADD CONSTRAINT FK_PERFORMANCE_ON_SITE FOREIGN KEY (site_id) REFERENCES site (id);

ALTER TABLE seat_group
    ADD CONSTRAINT FK_SEATGROUP_ON_SITE FOREIGN KEY (site_id) REFERENCES site (id);

ALTER TABLE seat
    ADD CONSTRAINT FK_SEAT_ON_SEAT_GROUP FOREIGN KEY (seat_group_id) REFERENCES seat_group (id);

ALTER TABLE site
    ADD CONSTRAINT FK_SITE_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE manager
    ALTER COLUMN company_id SET NOT NULL;