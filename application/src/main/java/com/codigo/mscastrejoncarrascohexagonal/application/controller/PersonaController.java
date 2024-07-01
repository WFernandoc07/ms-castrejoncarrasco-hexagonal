package com.codigo.mscastrejoncarrascohexagonal.application.controller;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.in.PersonaServiceIn;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping
    public ResponseEntity<ResponseBase> registrar(@RequestBody RequestPersona persona) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(persona));
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<ResponseBase> actualizar(@RequestBody RequestPersona persona, @PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.actualizarPersonaIn(id, persona));
    }

}
