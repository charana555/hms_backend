package tech.charana.hms.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "hospitals",
        indexes = { @Index(name = "idx_hospitals_name", columnList = "name") })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "registration_number", length = 100, unique = true)
    private String registrationNumber;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(name = "primary_contact_name")
    private String primaryContactName;

    @Embedded
    private Address address;

    private String timezone;   // e.g. "Asia/Kolkata"
    private String currency;   // e.g. "INR"

    @Column(name = "online_appointment_enabled", nullable = false)
    private boolean onlineAppointmentEnabled = true;

    @Column(name = "logo_url")
    private String logoUrl;

    private String website;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HospitalStatus status = HospitalStatus.ACTIVE;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
