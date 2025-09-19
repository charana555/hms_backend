package tech.charana.hms.mapper;

import tech.charana.hms.dto.HospitalDTO;
import tech.charana.hms.model.Hospital;

public class HospitalMapper {

    public static HospitalDTO toDto(Hospital h) {
        if (h == null) return null;
        return HospitalDTO.builder()
                .id(h.getId())
                .name(h.getName())
                .registrationNumber(h.getRegistrationNumber())
                .email(h.getEmail())
                .phone(h.getPhone())
                .primaryContactName(h.getPrimaryContactName())
                .address(h.getAddress())
                .timezone(h.getTimezone())
                .currency(h.getCurrency())
                .onlineAppointmentEnabled(h.isOnlineAppointmentEnabled())
                .logoUrl(h.getLogoUrl())
                .website(h.getWebsite())
                .status(h.getStatus())
                .metadata(h.getMetadata())
                .createdAt(h.getCreatedAt())
                .updatedAt(h.getUpdatedAt())
                .build();
    }

    public static Hospital toEntity(HospitalDTO dto) {
        if (dto == null) return null;
        return Hospital.builder()
                .id(dto.getId())
                .name(dto.getName())
                .registrationNumber(dto.getRegistrationNumber())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .primaryContactName(dto.getPrimaryContactName())
                .address(dto.getAddress())
                .timezone(dto.getTimezone())
                .currency(dto.getCurrency())
                .onlineAppointmentEnabled(dto.isOnlineAppointmentEnabled())
                .logoUrl(dto.getLogoUrl())
                .website(dto.getWebsite())
                .status(dto.getStatus() == null ? tech.charana.hms.model.HospitalStatus.ACTIVE : dto.getStatus())
                .metadata(dto.getMetadata())
                .build();
    }

    // merge DTO into entity (for update)
    public static void merge(HospitalDTO dto, Hospital entity) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getRegistrationNumber() != null) entity.setRegistrationNumber(dto.getRegistrationNumber());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if (dto.getPrimaryContactName() != null) entity.setPrimaryContactName(dto.getPrimaryContactName());
        if (dto.getAddress() != null) entity.setAddress(dto.getAddress());
        if (dto.getTimezone() != null) entity.setTimezone(dto.getTimezone());
        if (dto.getCurrency() != null) entity.setCurrency(dto.getCurrency());
        entity.setOnlineAppointmentEnabled(dto.isOnlineAppointmentEnabled());
        if (dto.getLogoUrl() != null) entity.setLogoUrl(dto.getLogoUrl());
        if (dto.getWebsite() != null) entity.setWebsite(dto.getWebsite());
        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
        if (dto.getMetadata() != null) entity.setMetadata(dto.getMetadata());
    }
}
