package tech.charana.hms.service.impl;

import tech.charana.hms.dto.HospitalDTO;
import tech.charana.hms.mapper.HospitalMapper;
import tech.charana.hms.model.Hospital;
import tech.charana.hms.repository.HospitalRepository;
import tech.charana.hms.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository repo;

    @Autowired
    public HospitalServiceImpl(HospitalRepository repo) {
        this.repo = repo;
    }

    @Override
    public HospitalDTO createHospital(HospitalDTO dto) {
        Hospital entity = HospitalMapper.toEntity(dto);
        Hospital saved = repo.save(entity);
        return HospitalMapper.toDto(saved);
    }

    @Override
    public HospitalDTO updateHospital(UUID id, HospitalDTO dto) {
        Hospital existing = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found: " + id));
        HospitalMapper.merge(dto, existing);
        Hospital updated = repo.save(existing);
        return HospitalMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public HospitalDTO getHospital(UUID id) {
        return repo.findByIdAndDeletedFalse(id)
                .map(HospitalMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HospitalDTO> listHospitals(String search, Pageable pageable) {
        String q = (search == null) ? "" : search;
        return repo.findByDeletedFalseAndNameContainingIgnoreCase(q, pageable)
                .map(HospitalMapper::toDto);
    }

    @Override
    public void softDeleteHospital(UUID id) {
        Hospital existing = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found: " + id));
        existing.setDeleted(true);
        existing.setDeletedAt(OffsetDateTime.now());
        repo.save(existing);
    }

    @Override
    public HospitalDTO changeStatus(UUID id, String status) {
        Hospital existing = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found: " + id));
        existing.setStatus(Enum.valueOf(tech.charana.hms.model.HospitalStatus.class, status));
        return HospitalMapper.toDto(repo.save(existing));
    }
}

