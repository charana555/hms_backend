package tech.charana.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.charana.hms.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    List<Patient> findByHospitalId(UUID hospitalId);
    Optional<Patient> findByIdAndHospitalId(UUID patientId, UUID hospitalId); // <-- add this
}
