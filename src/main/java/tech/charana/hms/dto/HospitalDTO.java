package tech.charana.hms.dto;


import tech.charana.hms.model.Address;
import tech.charana.hms.model.HospitalStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDTO {
    private UUID id;

    @NotBlank
    private String name;

    private String registrationNumber;

    @Email
    private String email;

    private String phone;

    private String primaryContactName;

    private Address address;

    private String timezone;
    private String currency;
    private boolean onlineAppointmentEnabled = true;
    private String logoUrl;
    private String website;

    private HospitalStatus status;

    private Map<String, Object> metadata;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
