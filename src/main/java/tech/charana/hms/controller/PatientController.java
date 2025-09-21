package tech.charana.hms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.charana.hms.dto.PatientRequest;
import tech.charana.hms.dto.PatientResponse;
import tech.charana.hms.service.PatientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hospitals/{hospitalId}/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(
            @PathVariable UUID hospitalId,
            @RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.createPatient(hospitalId, request));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getPatients(@PathVariable UUID hospitalId) {
        // updated to call the correct service method
        return ResponseEntity.ok(patientService.getPatientsByHospital(hospitalId));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponse> getPatient(
            @PathVariable UUID hospitalId,
            @PathVariable UUID patientId) {
        return ResponseEntity.ok(patientService.getPatient(hospitalId, patientId));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable UUID hospitalId,
            @PathVariable UUID patientId,
            @RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(hospitalId, patientId, request));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable UUID hospitalId,
            @PathVariable UUID patientId) {
        patientService.deletePatient(hospitalId, patientId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{patientId}/status")
    public ResponseEntity<PatientResponse> updatePatientStatus(
            @PathVariable UUID hospitalId,
            @PathVariable UUID patientId,
            @RequestParam String status) {
        return ResponseEntity.ok(patientService.updatePatientStatus(hospitalId, patientId, status));
    }
}
