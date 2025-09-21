package tech.charana.hms.service;

import java.util.List;
import java.util.UUID;
import tech.charana.hms.dto.PatientRequest;
import tech.charana.hms.dto.PatientResponse;

public interface PatientService {
    PatientResponse createPatient(UUID hospitalId, PatientRequest request);
    List<PatientResponse> getPatientsByHospital(UUID hospitalId);
    PatientResponse getPatient(UUID hospitalId, UUID patientId); // <-- add this
    PatientResponse updatePatient(UUID hospitalId, UUID patientId, PatientRequest request);
    void deletePatient(UUID hospitalId, UUID patientId);
    PatientResponse updatePatientStatus(UUID hospitalId, UUID patientId, String status);
}
