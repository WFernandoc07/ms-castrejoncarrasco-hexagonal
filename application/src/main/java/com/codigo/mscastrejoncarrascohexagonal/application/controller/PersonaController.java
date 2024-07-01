package com.codigo.mscastrejoncarrascohexagonal.application.controller;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.in.PersonaServiceIn;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase> obtenerPersonaPorNumDoc(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaPorIdIn(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PersonaDTO>> listarPersonas(){
        List<PersonaDTO> personas = personaServiceIn.buscarTodosIn();
        return ResponseEntity.ok(personas);
    }

}
