package tech.charana.hms.dto;

import lombok.*;
import tech.charana.hms.model.AppointmentStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponse {
    private UUID id;
    private UUID hospitalId;
    private UUID doctorId;
    private UUID patientId;
    private OffsetDateTime appointmentTime;
    private AppointmentStatus status;
    private String notes;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
