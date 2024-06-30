package com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPersona {
    private String numDoc;
    private int edad;
    private String cargo;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private String direccion;
}
