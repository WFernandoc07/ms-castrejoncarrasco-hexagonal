package com.codigo.mscastrejoncarrascohexagonal.infraestructure.dao;

import com.codigo.mscastrejoncarrascohexagonal.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Boolean existsByNumDoc(String numDoc);

    PersonaEntity findByNumDoc(String numDoc);

    List<PersonaEntity> findByEstado(Boolean estado);
}
