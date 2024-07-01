package com.codigo.mscastrejoncarrascohexagonal.domain.impl;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.in.PersonaServiceIn;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.out.PersonaServiceOut;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {
    private final PersonaServiceOut personaServiceOut;

    @Override
    public ResponseBase crearPersonaIn(RequestPersona persona) {
        return personaServiceOut.crearPersonaOut(persona);
    }

    @Override
    public ResponseBase actualizarPersonaIn(Long id, RequestPersona persona) {
        return personaServiceOut.actualizarPersonaOut(id, persona);
    }

    @Override
    public ResponseBase obtenerPersonaPorIdIn(String numDoc) {
        return personaServiceOut.obtenerPersonaPorIdOut(numDoc);
    }

    @Override
    public List<PersonaDTO> buscarTodosIn() {
        return personaServiceOut.buscarTodosOut();
    }


}
