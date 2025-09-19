package tech.charana.hms.repository;

import tech.charana.hms.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    Page<Hospital> findByDeletedFalseAndNameContainingIgnoreCase(String name, Pageable pageable);
    Optional<Hospital> findByIdAndDeletedFalse(UUID id);
}
