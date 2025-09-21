CREATE TABLE IF NOT EXISTS doctors (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    hospital_id uuid NOT NULL,
    first_name varchar(100) NOT NULL,
    last_name varchar(100),
    specialization varchar(150),
    email varchar(255) UNIQUE,
    phone varchar(50),
    consultation_fee numeric(10,2),
    status varchar(20) NOT NULL DEFAULT 'ACTIVE',
    metadata jsonb DEFAULT '{}'::jsonb,
    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz NOT NULL DEFAULT now(),

    CONSTRAINT fk_doctors_hospital FOREIGN KEY (hospital_id)
        REFERENCES hospitals(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_doctors_specialization ON doctors (lower(specialization));
