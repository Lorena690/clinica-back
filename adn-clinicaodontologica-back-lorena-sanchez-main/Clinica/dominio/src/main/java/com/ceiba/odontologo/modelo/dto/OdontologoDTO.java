package com.ceiba.odontologo.modelo.dto;

import com.ceiba.odontologo.modelo.entidad.Odontologo;

public class OdontologoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public Odontologo convertirAOdontologo() {
        return Odontologo.reconstruir(id, nombre, apellido, matricula);
    }

    public OdontologoDTO(Long id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMatricula() {
        return matricula;
    }
}
