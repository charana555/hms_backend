package tech.charana.hms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.charana.hms.dto.AppointmentRequest;
import tech.charana.hms.dto.AppointmentResponse;
import tech.charana.hms.service.AppointmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hospitals/{hospitalId}/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(
            @PathVariable UUID hospitalId,
            @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.createAppointment(hospitalId, request));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAppointments(@PathVariable UUID hospitalId) {
        return ResponseEntity.ok(appointmentService.getAppointments(hospitalId));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> getAppointment(
            @PathVariable UUID hospitalId,
            @PathVariable UUID appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointment(hospitalId, appointmentId));
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> updateAppointment(
            @PathVariable UUID hospitalId,
            @PathVariable UUID appointmentId,
            @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.updateAppointment(hospitalId, appointmentId, request));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable UUID hospitalId,
            @PathVariable UUID appointmentId) {
        appointmentService.deleteAppointment(hospitalId, appointmentId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponse> updateAppointmentStatus(
            @PathVariable UUID hospitalId,
            @PathVariable UUID appointmentId,
            @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(hospitalId, appointmentId, status));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctor(
            @PathVariable UUID hospitalId,
            @PathVariable UUID doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(hospitalId, doctorId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByPatient(
            @PathVariable UUID hospitalId,
            @PathVariable UUID patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(hospitalId, patientId));
    }
}
