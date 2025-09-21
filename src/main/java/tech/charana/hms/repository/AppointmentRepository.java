package tech.charana.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.charana.hms.model.Appointment;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    // Get all appointments for a hospital
    List<Appointment> findByHospitalId(UUID hospitalId);

    // Get all appointments for a specific doctor in a hospital
    List<Appointment> findByHospitalIdAndDoctorId(UUID hospitalId, UUID doctorId);

    // Get all appointments for a specific patient in a hospital
    List<Appointment> findByHospitalIdAndPatientId(UUID hospitalId, UUID patientId);
}
