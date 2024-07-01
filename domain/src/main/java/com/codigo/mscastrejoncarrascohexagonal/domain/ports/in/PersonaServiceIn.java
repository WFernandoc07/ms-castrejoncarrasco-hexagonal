package com.codigo.mscastrejoncarrascohexagonal.domain.ports.in;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;

import java.util.List;

public interface PersonaServiceIn {
    ResponseBase crearPersonaIn(RequestPersona persona);
    ResponseBase actualizarPersonaIn(Long id, RequestPersona persona);
    ResponseBase obtenerPersonaPorIdIn(String numDoc);
    List<PersonaDTO> buscarTodosIn();
    ResponseBase eliminarPersonaIn(Long id);
}
