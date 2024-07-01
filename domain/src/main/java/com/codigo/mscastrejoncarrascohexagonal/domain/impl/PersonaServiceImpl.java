package com.codigo.mscastrejoncarrascohexagonal.domain.impl;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.in.PersonaServiceIn;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.out.PersonaServiceOut;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {
    private final PersonaServiceOut personaServiceOut;

    @Override
    public ResponseBase crearPersonaIn(RequestPersona persona) {
        return personaServiceOut.crearPersonaOut(persona);
    }
}
