-- Liquibase formatted sql
-- DBMS: postgres
-- Fail: Check database connection or permissions. For extension errors, ensure PostgreSQL version supports pgcrypto.
-- Labels: table,file_metadata

-- Changeset adsimka:00301
-- Comment: Create table for file metadata
-- Date: 2026-02-21
CREATE TABLE IF NOT EXISTS storage.file_metadata
(
    id              UUID DEFAULT gen_random_uuid(),
    original_name   VARCHAR(1000) NOT NULL,
    stored_name     VARCHAR(1000) NOT NULL,      -- UUID-based name in object storage
    content_type    VARCHAR(200) NOT NULL,       -- mime-type
    size_bytes      BIGINT NOT NULL,
    bucket          VARCHAR(200) NOT NULL,       -- MiniO bucket
    storage_path    VARCHAR(2000) NOT NULL,      -- full path in object storage
    uploaded_by     UUID NOT NULL,               -- userId
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT file_metadata_pkey PRIMARY KEY (id)
);

-- Changeset adsimka:00302
-- Comment: Create indexes for performance optimization
-- Date: 2026-02-21
CREATE INDEX IF NOT EXISTS idx_files_bucket ON storage.file_metadata(bucket);
CREATE INDEX IF NOT EXISTS idx_files_uploaded_by ON storage.file_metadata(uploaded_by);