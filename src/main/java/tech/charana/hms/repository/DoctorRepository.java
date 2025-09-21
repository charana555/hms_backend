package tech.charana.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.charana.hms.model.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    List<Doctor> findByHospitalId(UUID hospitalId);
}

