package com.codigo.mscastrejoncarrascohexagonal.domain.ports.in;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;

public interface PersonaServiceIn {
    PersonaDTO crearPersonaIn(RequestPersona persona);

}
