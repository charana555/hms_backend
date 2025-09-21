package tech.charana.hms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.charana.hms.dto.AppointmentRequest;
import tech.charana.hms.dto.AppointmentResponse;
import tech.charana.hms.model.Appointment;
import tech.charana.hms.model.AppointmentStatus;
import tech.charana.hms.repository.AppointmentRepository;
import tech.charana.hms.repository.DoctorRepository;
import tech.charana.hms.repository.PatientRepository;
import tech.charana.hms.repository.HospitalRepository;
import tech.charana.hms.service.AppointmentService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public AppointmentResponse createAppointment(UUID hospitalId, AppointmentRequest request) {
        var hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));
        var doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        var patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Appointment appointment = Appointment.builder()
                .hospital(hospital)
                .doctor(doctor)
                .patient(patient)
                .appointmentTime(request.getAppointmentTime())
                .status(AppointmentStatus.SCHEDULED)
                .notes(request.getNotes())
                .build();

        return mapToResponse(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentResponse> getAppointments(UUID hospitalId) {
        return appointmentRepository.findByHospitalId(hospitalId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(UUID hospitalId, UUID doctorId) {
        return appointmentRepository.findByHospitalIdAndDoctorId(hospitalId, doctorId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(UUID hospitalId, UUID patientId) {
        return appointmentRepository.findByHospitalIdAndPatientId(hospitalId, patientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse getAppointment(UUID hospitalId, UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (!appointment.getHospital().getId().equals(hospitalId)) {
            throw new IllegalArgumentException("Appointment does not belong to hospital");
        }

        return mapToResponse(appointment);
    }

    @Override
    public AppointmentResponse updateAppointment(UUID hospitalId, UUID appointmentId, AppointmentRequest request) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (!appointment.getHospital().getId().equals(hospitalId)) {
            throw new IllegalArgumentException("Appointment does not belong to hospital");
        }

        var doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        var patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setNotes(request.getNotes());

        return mapToResponse(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(UUID hospitalId, UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (!appointment.getHospital().getId().equals(hospitalId)) {
            throw new IllegalArgumentException("Appointment does not belong to hospital");
        }

        appointmentRepository.delete(appointment);
    }

    @Override
    public AppointmentResponse updateAppointmentStatus(UUID hospitalId, UUID appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (!appointment.getHospital().getId().equals(hospitalId)) {
            throw new IllegalArgumentException("Appointment does not belong to hospital");
        }

        appointment.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
        return mapToResponse(appointmentRepository.save(appointment));
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .hospitalId(appointment.getHospital().getId())
                .doctorId(appointment.getDoctor().getId())
                .patientId(appointment.getPatient().getId())
                .appointmentTime(appointment.getAppointmentTime())
                .status(AppointmentStatus.valueOf(appointment.getStatus().name()))
                .notes(appointment.getNotes())
                .build();
    }
}
