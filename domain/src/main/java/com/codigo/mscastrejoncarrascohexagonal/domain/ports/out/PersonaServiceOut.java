package com.codigo.mscastrejoncarrascohexagonal.domain.ports.out;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;

import java.util.List;

public interface PersonaServiceOut {
    ResponseBase crearPersonaOut(RequestPersona persona);
    ResponseBase actualizarPersonaOut(Long id, RequestPersona persona);
    ResponseBase obtenerPersonaPorIdOut(String numDoc);
    List<PersonaDTO> buscarTodosOut();
    ResponseBase eliminarPersonaOut(Long id);

    List<PersonaDTO> buscarPersonaPoCriterioOut(String nombre, String telefono, String numDoc);
}
