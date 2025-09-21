package tech.charana.hms.service;

import tech.charana.hms.dto.AppointmentRequest;
import tech.charana.hms.dto.AppointmentResponse;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    AppointmentResponse createAppointment(UUID hospitalId, AppointmentRequest request);

    List<AppointmentResponse> getAppointments(UUID hospitalId);

    List<AppointmentResponse> getAppointmentsByDoctor(UUID hospitalId, UUID doctorId);

    List<AppointmentResponse> getAppointmentsByPatient(UUID hospitalId, UUID patientId);

    AppointmentResponse getAppointment(UUID hospitalId, UUID appointmentId);

    AppointmentResponse updateAppointment(UUID hospitalId, UUID appointmentId, AppointmentRequest request);

    void deleteAppointment(UUID hospitalId, UUID appointmentId);

    AppointmentResponse updateAppointmentStatus(UUID hospitalId, UUID appointmentId, String status);
}
