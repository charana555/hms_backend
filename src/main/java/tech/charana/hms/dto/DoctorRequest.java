package tech.charana.hms.dto;

import lombok.Data;
import tech.charana.hms.model.DoctorStatus;

@Data
public class DoctorRequest {
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private DoctorStatus status;
}
