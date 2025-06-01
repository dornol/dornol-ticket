ALTER TABLE performance
DROP
CONSTRAINT fk_performance_on_site;

ALTER TABLE performance_site
DROP
CONSTRAINT fk_performancesite_on_performance;

ALTER TABLE performance_site
DROP
CONSTRAINT fk_performancesite_on_site;

CREATE TABLE performance_schedule
(
    id                 BIGINT  NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    performance_date   date    NOT NULL,
    performance_time   time WITHOUT TIME ZONE NOT NULL,
    performance_id     BIGINT  NOT NULL,
    site_id            BIGINT  NOT NULL,
    CONSTRAINT pk_performance_schedule PRIMARY KEY (id)
);

ALTER TABLE performance
    ADD company_id BIGINT;

ALTER TABLE performance
    ALTER COLUMN company_id SET NOT NULL;

CREATE INDEX performance_schedule_index ON performance_schedule (performance_id, site_id);

ALTER TABLE performance
    ADD CONSTRAINT FK_PERFORMANCE_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE performance_schedule
    ADD CONSTRAINT FK_PERFORMANCE_SCHEDULE_ON_PERFORMANCE FOREIGN KEY (performance_id) REFERENCES performance (id);

ALTER TABLE performance_schedule
    ADD CONSTRAINT FK_PERFORMANCE_SCHEDULE_ON_SITE FOREIGN KEY (site_id) REFERENCES site (id);

DROP TABLE performance_site CASCADE;

ALTER TABLE performance
DROP
COLUMN site_id;