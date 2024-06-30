package com.codigo.mscastrejoncarrascohexagonal.application.controller;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona persona) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(persona));
    }
}
