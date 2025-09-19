package tech.charana.hms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Column(name = "address_line1")
    private String line1;

    @Column(name = "address_line2")
    private String line2;

    private String city;
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;
}
