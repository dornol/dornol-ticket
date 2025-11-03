ALTER TABLE site
    DROP CONSTRAINT fk_site_on_seating_map_file;

CREATE TABLE file_metadata
(
    id                 BIGINT       NOT NULL,
    deleted_by         BIGINT,
    deleted_date       TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    uuid               UUID         NOT NULL,
    name               VARCHAR(255) NOT NULL,
    bucket             VARCHAR(255) NOT NULL,
    object_key         VARCHAR(512) NOT NULL,
    size               BIGINT       NOT NULL,
    checksum           VARCHAR(255) NOT NULL,
    mime_type          VARCHAR(50)  NOT NULL,
    extension          VARCHAR(50)  NOT NULL,
    media_type         VARCHAR(50)  NOT NULL,
    CONSTRAINT pk_file_metadata PRIMARY KEY (id)
);

ALTER TABLE manager
    ADD approved_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE file_metadata
    ADD CONSTRAINT uc_file_metadata_checksum UNIQUE (checksum);

ALTER TABLE file_metadata
    ADD CONSTRAINT uc_file_metadata_uuid UNIQUE (uuid);

ALTER TABLE site
    ADD CONSTRAINT FK_SITE_ON_SEATING_MAP_FILE FOREIGN KEY (seating_map_file_id) REFERENCES file_metadata (id);

DROP TABLE common_file CASCADE;

ALTER TABLE manager
    DROP COLUMN approved_date;

ALTER TABLE reservation
    ALTER COLUMN price SET NOT NULL;

ALTER TABLE reservation_payment
    ALTER COLUMN price SET NOT NULL;