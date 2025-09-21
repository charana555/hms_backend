package tech.charana.hms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.charana.hms.dto.DoctorRequest;
import tech.charana.hms.dto.DoctorResponse;
import tech.charana.hms.model.Doctor;
import tech.charana.hms.model.DoctorStatus;
import tech.charana.hms.service.DoctorService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(
            @PathVariable UUID hospitalId,
            @RequestBody DoctorRequest request
    ) {
        return ResponseEntity.ok(doctorService.createDoctor(hospitalId, request));
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<List<Doctor>> getDoctors(@PathVariable UUID hospitalId) {
        return ResponseEntity.ok(doctorService.getDoctorsByHospital(hospitalId));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponse> updateDoctor(
            @PathVariable UUID doctorId,
            @RequestBody DoctorRequest request
    ) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctorId, request));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable UUID doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.noContent().build();
    }



    @PatchMapping("/{id}/status")
    public ResponseEntity<Doctor> updateDoctorStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, String> request
    ) {
        DoctorStatus status = DoctorStatus.valueOf(request.get("status"));
        return ResponseEntity.ok(doctorService.updateDoctorStatus(id, status));
    }

}
