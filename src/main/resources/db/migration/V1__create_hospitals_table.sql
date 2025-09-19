-- V1__create_hospitals_table.sql
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS hospitals (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name varchar(255) NOT NULL,
  registration_number varchar(100),
  email varchar(255),
  phone varchar(50),
  primary_contact_name varchar(255),
  address_line1 varchar(255),
  address_line2 varchar(255),
  city varchar(100),
  state varchar(100),
  postal_code varchar(30),
  country varchar(100),
  timezone varchar(50),
  currency varchar(10),
  online_appointment_enabled boolean NOT NULL DEFAULT true,
  logo_url text,
  website text,
  status varchar(20) NOT NULL DEFAULT 'ACTIVE',
  metadata jsonb DEFAULT '{}'::jsonb,
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now(),
  deleted boolean NOT NULL DEFAULT false,
  deleted_at timestamptz
);

-- Unique + indexes
CREATE UNIQUE INDEX IF NOT EXISTS uq_hospitals_registration_number ON hospitals (registration_number);
CREATE UNIQUE INDEX IF NOT EXISTS uq_hospitals_email ON hospitals (email);
CREATE INDEX IF NOT EXISTS idx_hospitals_name_lower ON hospitals (lower(name));
