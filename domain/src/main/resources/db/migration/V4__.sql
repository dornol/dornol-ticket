CREATE TABLE common_file
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
    location           VARCHAR(512) NOT NULL,
    size               BIGINT       NOT NULL,
    file_hash          VARCHAR(255) NOT NULL,
    mime_type          VARCHAR(255),
    extension          VARCHAR(255),
    media_type         VARCHAR(255),
    CONSTRAINT pk_commonfile PRIMARY KEY (id)
);

ALTER TABLE site
    ADD seating_map_file_id BIGINT;

ALTER TABLE site
    ALTER COLUMN seating_map_file_id SET NOT NULL;

ALTER TABLE common_file
    ADD CONSTRAINT uc_commonfile_file_hash UNIQUE (file_hash);

ALTER TABLE common_file
    ADD CONSTRAINT uc_commonfile_uuid UNIQUE (uuid);

ALTER TABLE site
    ADD CONSTRAINT uc_site_seating_map_file UNIQUE (seating_map_file_id);

ALTER TABLE site
    ADD CONSTRAINT FK_SITE_ON_SEATING_MAP_FILE FOREIGN KEY (seating_map_file_id) REFERENCES common_file (id);