package com.codigo.mscastrejoncarrascohexagonal.domain.ports.out;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;

public interface PersonaServiceOut {
    ResponseBase crearPersonaOut(RequestPersona persona);
    ResponseBase actualizarPersonaOut(Long id, RequestPersona persona);
}
