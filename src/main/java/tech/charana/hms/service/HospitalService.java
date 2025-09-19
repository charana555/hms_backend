package tech.charana.hms.service;

import tech.charana.hms.dto.HospitalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HospitalService {
    HospitalDTO createHospital(HospitalDTO dto);
    HospitalDTO updateHospital(UUID id, HospitalDTO dto);
    HospitalDTO getHospital(UUID id);
    Page<HospitalDTO> listHospitals(String search, Pageable pageable);
    void softDeleteHospital(UUID id);
    HospitalDTO changeStatus(UUID id, String status); // simple status change
}
