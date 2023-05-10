package com.ceiba.odontologo.modelo.dto;

public class OdontologoDTOTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoDTOTestDataBuilder conOdontologoPorDefecto() {
        this.id = 1l;
        this.nombre = "Lorena";
        this.apellido = "Sanchez";
        this.matricula = "1234";
        return this;
    }

    public OdontologoDTO crear() {
        return new OdontologoDTO(id, nombre, apellido, matricula);
    }

    public OdontologoDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public OdontologoDTOTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public OdontologoDTOTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public OdontologoDTOTestDataBuilder conMatricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

}
