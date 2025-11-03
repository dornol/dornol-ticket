ALTER TABLE manager
    ADD approved BOOLEAN;

ALTER TABLE manager
    ADD approved_by BIGINT;

ALTER TABLE manager
    ADD approved_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE manager
    ADD deleted BOOLEAN;

ALTER TABLE manager
    ADD deleted_by BIGINT;

ALTER TABLE manager
    ADD deleted_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE manager
    ALTER COLUMN manager_role SET DATA TYPE VARCHAR(20);

UPDATE manager
SET approved = false
WHERE approved IS NULL;

UPDATE manager
SET deleted = false
WHERE deleted IS NULL;

UPDATE manager
SET manager_role = 'BUSINESS_ADMIN'
WHERE manager_role = 'MANAGER';

UPDATE manager
SET manager_role = 'SYSTEM_ADMIN'
WHERE manager_role = 'SYSTEM';

ALTER TABLE manager
    ALTER COLUMN approved SET NOT NULL;

ALTER TABLE manager
    ALTER COLUMN deleted SET NOT NULL;

ALTER TABLE company
DROP
COLUMN tel_number;

ALTER TABLE manager
    ADD company_id BIGINT;

ALTER TABLE manager
    ADD CONSTRAINT FK_MANAGER_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);