-- Liquibase formatted sql
-- DBMS: postgres
-- Fail: Check database connection or permissions. For extension errors, ensure PostgreSQL version supports pgcrypto.
-- Labels: schema, storage

-- Changeset adsimka:00201
-- Comment: Add storage schema
-- Date: 2026-02-21
CREATE SCHEMA IF NOT EXISTS storage;