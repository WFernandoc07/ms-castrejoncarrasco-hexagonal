package com.codigo.mscastrejoncarrascohexagonal.infraestructure.specification;


import com.codigo.mscastrejoncarrascohexagonal.infraestructure.entity.PersonaEntity;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class PersonaSpecification {

    public static Specification<PersonaEntity> nombreContains(String nombre) {
        return(root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(nombre)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
        };
    }

    public static Specification<PersonaEntity> telefonoContains(String telefono) {
        return(root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(telefono)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("telefono"), "%" + telefono + "%");
        };
    }

    public static Specification<PersonaEntity> numDocContains(String numDoc) {
        return(root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(numDoc)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("numDoc"), "%" + numDoc + "%");
        };
    }

    public static Specification<PersonaEntity> estadoIsTrue(){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("estado"));
    }
}
