package tech.charana.hms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.charana.hms.dto.DoctorRequest;
import tech.charana.hms.dto.DoctorResponse;
import tech.charana.hms.model.Doctor;
import tech.charana.hms.model.Hospital;
import tech.charana.hms.model.DoctorStatus;
import tech.charana.hms.repository.DoctorRepository;
import tech.charana.hms.repository.HospitalRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    public Doctor createDoctor(UUID hospitalId, DoctorRequest request) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Doctor doctor = Doctor.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .specialization(request.getSpecialization())
                .email(request.getEmail())
                .phone(request.getPhone())
                .status(request.getStatus() != null ? request.getStatus() : DoctorStatus.ACTIVE)
                .hospital(hospital)
                .build();

        return doctorRepository.save(doctor);
    }


    public List<Doctor> getDoctorsByHospital(UUID hospitalId) {
        return doctorRepository.findByHospitalId(hospitalId);
    }

    public DoctorResponse updateDoctor(UUID doctorId, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        if (request.getFirstName() != null) doctor.setFirstName(request.getFirstName());
        if (request.getLastName() != null) doctor.setLastName(request.getLastName());
        if (request.getSpecialization() != null) doctor.setSpecialization(request.getSpecialization());
        if (request.getEmail() != null) doctor.setEmail(request.getEmail());
        if (request.getPhone() != null) doctor.setPhone(request.getPhone());
        if (request.getStatus() != null) doctor.setStatus(request.getStatus()); // ✅ add this

        return mapToResponse(doctorRepository.save(doctor));
    }



    public void deleteDoctor(UUID doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        doctorRepository.delete(doctor);
    }

    public Doctor updateDoctorStatus(UUID id, DoctorStatus status) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setStatus(status);
        return doctorRepository.save(doctor);
    }


    private DoctorResponse mapToResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        response.setId(doctor.getId());
        response.setHospitalId(doctor.getHospital().getId());
        response.setFirstName(doctor.getFirstName());
        response.setLastName(doctor.getLastName());
        response.setSpecialization(doctor.getSpecialization());
        response.setEmail(doctor.getEmail());
        response.setPhone(doctor.getPhone());
        response.setConsultationFee(doctor.getConsultationFee());
        response.setStatus(doctor.getStatus().name());
        response.setMetadata(doctor.getMetadata());
        return response;
    }
}
