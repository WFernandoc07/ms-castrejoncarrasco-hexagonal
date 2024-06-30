package com.codigo.mscastrejoncarrascohexagonal.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "persona_examen")
@Getter
@Setter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 150, nullable = false)
    private String apellido;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "cargo", length = 150)
    private String cargo;

    @Column(name = "tipo_docu", length = 50, nullable = false)
    private String tipoDoc;

    @Column(name = "num_docu", length = 15, nullable = false)
    private String numDoc;

    @Column(name = "departanento", length = 50, nullable = false)
    private String departamento;

    @Column(name = "salario")
    private double salario;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "correo", length = 60, nullable = false)
    private String correo;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Column(name = "direccion",length = 150, nullable = false)
    private String direccion;

    @Column(name = "date_create", nullable = false)
    private Timestamp dateCrea;

    @Column(name = "usua_create", length = 50, nullable = false)
    private String usuaCrea;

    @Column(name = "date_update")
    private Timestamp dateUdpate;

    @Column(name = "usua_update", length = 50)
    private String usuaUpdate;

    @Column(name = "date_delete")
    private Timestamp dateDelete;

    @Column(name = "usua_delete", length = 50)
    private String usuaDelete;
}
