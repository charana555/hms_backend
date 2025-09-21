package tech.charana.hms.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {
    private UUID doctorId;
    private UUID patientId;
    private OffsetDateTime appointmentTime;
    private String notes;
}
