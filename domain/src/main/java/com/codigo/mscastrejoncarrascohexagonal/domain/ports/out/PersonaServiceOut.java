package com.codigo.mscastrejoncarrascohexagonal.domain.ports.out;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;

public interface PersonaServiceOut {
    PersonaDTO crearPersonaOut(RequestPersona persona);
}
