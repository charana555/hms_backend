package tech.charana.hms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.charana.hms.dto.PatientRequest;
import tech.charana.hms.dto.PatientResponse;
import tech.charana.hms.model.Patient;
import tech.charana.hms.model.PatientStatus;
import tech.charana.hms.repository.PatientRepository;
import tech.charana.hms.repository.HospitalRepository;
import tech.charana.hms.service.PatientService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public PatientResponse createPatient(UUID hospitalId, PatientRequest request) {
        var hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));

        Patient patient = Patient.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .status(String.valueOf(PatientStatus.ACTIVE))
                .hospital(hospital)
                .build();

        return mapToResponse(patientRepository.save(patient));
    }

    @Override
    public List<PatientResponse> getPatientsByHospital(UUID hospitalId) {
        return patientRepository.findByHospitalId(hospitalId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse getPatient(UUID hospitalId, UUID patientId) {
        Patient patient = patientRepository.findByIdAndHospitalId(patientId, hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found for this hospital"));
        return mapToResponse(patient);
    }

    @Override
    public PatientResponse updatePatient(UUID hospitalId, UUID patientId, PatientRequest request) {
        Patient patient = patientRepository.findByIdAndHospitalId(patientId, hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found for this hospital"));

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());

        return mapToResponse(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(UUID hospitalId, UUID patientId) {
        Patient patient = patientRepository.findByIdAndHospitalId(patientId, hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found for this hospital"));
        patientRepository.delete(patient);
    }

    @Override
    public PatientResponse updatePatientStatus(UUID hospitalId, UUID patientId, String status) {
        Patient patient = patientRepository.findByIdAndHospitalId(patientId, hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found for this hospital"));

        PatientStatus newStatus;
        try {
            newStatus = PatientStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid patient status: " + status);
        }

        patient.setStatus(String.valueOf(newStatus));
        return mapToResponse(patientRepository.save(patient));
    }

    // Helper method to convert entity to response DTO
    private PatientResponse mapToResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .status(patient.getStatus())
                .hospitalId(patient.getHospital().getId())
                .build();
    }
}
