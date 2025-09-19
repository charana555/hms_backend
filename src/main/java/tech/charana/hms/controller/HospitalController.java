package tech.charana.hms.controller;

import tech.charana.hms.dto.HospitalDTO;
import tech.charana.hms.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/hospitals")
@Validated
public class HospitalController {

    private final HospitalService service;

    @Autowired
    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HospitalDTO> create(@Valid @RequestBody HospitalDTO dto) {
        HospitalDTO created = service.createHospital(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getHospital(id));
    }

    @GetMapping
    public ResponseEntity<Page<HospitalDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<HospitalDTO> result = service.listHospitals(search, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalDTO> update(@PathVariable UUID id, @Valid @RequestBody HospitalDTO dto) {
        return ResponseEntity.ok(service.updateHospital(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.softDeleteHospital(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<HospitalDTO> changeStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(service.changeStatus(id, status));
    }
}
