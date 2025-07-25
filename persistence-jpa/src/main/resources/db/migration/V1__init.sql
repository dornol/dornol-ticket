CREATE TABLE company
(
    id                 BIGINT      NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(30) NOT NULL,
    business_number    VARCHAR(10) NOT NULL,
    tel_number         VARCHAR(11) NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE manager
(
    id                 BIGINT       NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    username           VARCHAR(18)  NOT NULL,
    password           VARCHAR(255) NOT NULL,
    name               VARCHAR(10)  NOT NULL,
    phone              VARCHAR(11)  NOT NULL,
    email              VARCHAR(320) NOT NULL,
    manager_role       VARCHAR(10)  NOT NULL,
    CONSTRAINT pk_manager PRIMARY KEY (id)
);

ALTER TABLE manager
    ADD CONSTRAINT uc_manager_username UNIQUE (username);