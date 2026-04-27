package com.example.pizzeria.controller;

import com.example.pizzeria.dto.border.BorderRequest;
import com.example.pizzeria.dto.border.BorderResponse;
import com.example.pizzeria.mapper.BorderMapper;
import com.example.pizzeria.model.Border;
import com.example.pizzeria.service.BorderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borders")
public class BorderController {

    private final BorderService borderService;
    private final BorderMapper borderMapper;

    public BorderController(BorderService borderService, BorderMapper borderMapper) {
        this.borderService = borderService;
        this.borderMapper = borderMapper;
    }

    @GetMapping
    public ResponseEntity<List<BorderResponse>> findAll() {
        List<BorderResponse> responses = borderService.findAll().stream()
                .map(borderMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorderResponse> findById(@PathVariable Long id) {
        Border border = borderService.findById(id);
        return ResponseEntity.ok(borderMapper.toResponse(border));
    }

    @PostMapping
    public ResponseEntity<BorderResponse> create(@RequestBody @Valid BorderRequest request) {
        Border border = borderService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(borderMapper.toResponse(border));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorderResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid BorderRequest request
    ) {
        Border border = borderService.update(
                id,
                request
        );

        return ResponseEntity.ok(borderMapper.toResponse(border));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        borderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
