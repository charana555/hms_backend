package tech.charana.hms.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class DoctorResponse {
    private UUID id;
    private UUID hospitalId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private BigDecimal consultationFee;
    private String status;
    private Map<String, Object> metadata;
}