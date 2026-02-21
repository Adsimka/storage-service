-- Liquibase formatted sql
-- DBMS: postgres
-- Fail: Check database connection or permissions. For extension errors, ensure PostgreSQL version supports pgcrypto.
-- Labels: pgcrypto

-- Changeset adsimka:00101
-- Comment: Add pgcrypto extension for backward compatibility (PostgreSQL < 13)
-- Date: 2026-02-21
CREATE EXTENSION IF NOT EXISTS "pgcrypto";