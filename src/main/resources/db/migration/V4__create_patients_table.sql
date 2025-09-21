CREATE TABLE appointments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    hospital_id UUID NOT NULL,
    doctor_id UUID NOT NULL,
    patient_id UUID NOT NULL,
    appointment_time TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'SCHEDULED',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    notes TEXT,

    CONSTRAINT fk_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id),
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patients(id)
);

-- Optional index for faster lookups
CREATE INDEX idx_appointments_hospital_id ON appointments(hospital_id);
CREATE INDEX idx_appointments_doctor_id ON appointments(doctor_id);
CREATE INDEX idx_appointments_patient_id ON appointments(patient_id);
