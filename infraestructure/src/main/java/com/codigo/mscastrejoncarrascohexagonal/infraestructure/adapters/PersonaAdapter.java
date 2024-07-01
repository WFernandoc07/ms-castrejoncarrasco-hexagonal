package com.codigo.mscastrejoncarrascohexagonal.infraestructure.adapters;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.constants.Constantes;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request.RequestPersona;
import com.codigo.mscastrejoncarrascohexagonal.domain.exceptions.personalizada.PersonaException;
import com.codigo.mscastrejoncarrascohexagonal.domain.ports.out.PersonaServiceOut;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseReniec;
import com.codigo.mscastrejoncarrascohexagonal.infraestructure.dao.PersonaRepository;
import com.codigo.mscastrejoncarrascohexagonal.infraestructure.entity.PersonaEntity;
import com.codigo.mscastrejoncarrascohexagonal.infraestructure.mapper.PersonaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    private final RestTemplate restTemplate;

    @Value("${token}")
    private String token;

    @Override
    public ResponseBase crearPersonaOut(RequestPersona persona) {
        PersonaEntity personaEntity = getEntityRestTemplate(persona);
        Boolean existe = personaRepository.existsByNumDoc(persona.getNumDoc());
        if (existe){
            throw new PersonaException("--La Persona ya existe en el sistema--");
        }
        else {
            return new ResponseBase(Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO, Optional.of(personaMapper.mapToDto(personaRepository.save(personaEntity))));
        }
    }

    @Override
    public ResponseBase actualizarPersonaOut(Long id, RequestPersona datosParaActualizar) {
        Optional<PersonaEntity> personaRecuperada = personaRepository.findById(id);

        if (personaRecuperada.isPresent()){
            PersonaEntity persona = personaRecuperada.get();
            if (persona.isEstado()){
                persona.setEdad(datosParaActualizar.getEdad());
                persona.setCargo(datosParaActualizar.getCargo());
                persona.setSalario(datosParaActualizar.getSalario());
                persona.setTelefono(datosParaActualizar.getTelefono());
                persona.setCorreo(datosParaActualizar.getCorreo());
                persona.setDepartamento(datosParaActualizar.getDepartamento());
                persona.setTelefono(datosParaActualizar.getTelefono());
                persona.setDateUdpate(getTime());
                persona.setUsuaUpdate(Constantes.USUARIO_ADMIN);
                return  new ResponseBase(Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO_ACTUALIZAR,
                        Optional.of(personaMapper.mapToDto(personaRepository.save(persona))));
            } else {
                throw new PersonaException("--La Persona no existe en el sistema--");
            }
        }else {
            throw new PersonaException("--La Persona no existe en el sistema--");
        }
    }

    @Override
    public ResponseBase obtenerPersonaPorIdOut(String numDoc) {
        PersonaEntity persona = personaRepository.findByNumDoc(numDoc);

        if(persona != null && persona.isEstado()){
            return new ResponseBase(Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO_OBTENER,
                    Optional.ofNullable(personaMapper.mapToDto(persona)));
        }else {
            throw new PersonaException("--La Persona no existe en el sistema--");
        }
    }

    @Override
    public List<PersonaDTO> buscarTodosOut() {
        List<PersonaEntity> listaPersonas = personaRepository.findByEstado(Constantes.ESTADO_ACTIVO);
        return listaPersonas.stream()
                .map(personaMapper::mapToDto)
                .toList();
    }

    @Override
    public ResponseBase eliminarPersonaOut(Long id) {
        Optional<PersonaEntity> personaRecuperada = personaRepository.findById(id);

        if (personaRecuperada.isPresent()){
            PersonaEntity persona = personaRecuperada.get();
            if (persona.isEstado()){
                persona.setEstado(false);
                persona.setDateDelete(getTime());
                persona.setUsuaDelete(Constantes.USUARIO_ADMIN);
                return new ResponseBase(Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO_ELIMINAR,
                        Optional.of(personaMapper.mapToDto(personaRepository.save(persona))));
            } else {
                throw new PersonaException("--La Persona no existe en el sistema--");
            }
        } else {
            throw new PersonaException("--La Persona no existe en el sistema--");
        }
    }

    private Timestamp getTime(){
        long current = System.currentTimeMillis();
        return new Timestamp(current);
    }


    private PersonaEntity getEntityRestTemplate(RequestPersona requestPersona) {
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero="+requestPersona.getNumDoc();

        try{
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)),
                    ResponseReniec.class
            );
            ResponseReniec responseReniec = response.getBody();
            PersonaEntity personaEntity = new PersonaEntity();

            personaEntity.setNombre(responseReniec.getNombres());
            personaEntity.setApellido(responseReniec.getApellidoPaterno().concat(" ").concat(responseReniec.getApellidoMaterno()));
            if (requestPersona.getEdad() != 0)
                personaEntity.setEdad(requestPersona.getEdad());
            else
                throw new NullPointerException("--Edad nula--");
            personaEntity.setCargo(requestPersona.getCargo());
            personaEntity.setTipoDoc(responseReniec.getTipoDocumento());
            personaEntity.setNumDoc(responseReniec.getNumeroDocumento());
            if(requestPersona.getDepartamento() != null)
                personaEntity.setDepartamento(requestPersona.getDepartamento());
            else
                throw new NullPointerException("--Departamento nulo--");
            personaEntity.setSalario(requestPersona.getSalario());
            if (requestPersona.getTelefono() != null)
                personaEntity.setTelefono(requestPersona.getTelefono());
            else
                throw new NullPointerException("--Nro Teléfono nulo--");

            personaEntity.setCorreo(requestPersona.getCorreo());
            personaEntity.setEstado(Constantes.ESTADO_ACTIVO);
            if(requestPersona.getDireccion() != null)
                personaEntity.setDireccion(requestPersona.getDireccion());
            else
                throw new NullPointerException("--Direccion nula--");
            personaEntity.setUsuaCrea(Constantes.USUARIO_ADMIN);
            personaEntity.setDateCrea(getTime());
            return personaEntity;

        }catch (HttpClientErrorException e){
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA" + e.getStatusCode());
        }
        return null;
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
