package com.ceiba.odontologo.modelo.entidad;

public class SolicitudCrearOdontologoTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public SolicitudCrearOdontologoTestDataBuilder conPacientePorDefecto() {
        this.id = 1l;
        this.nombre = "Lorena";
        this.apellido = "Sanchez";
        this.matricula = "1234";
        return this;
    }

    public SolicitudCrearOdontologo crear() {
        return new SolicitudCrearOdontologo(nombre, apellido, matricula);
    }


    public SolicitudCrearOdontologoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudCrearOdontologoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public SolicitudCrearOdontologoTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public SolicitudCrearOdontologoTestDataBuilder conMatricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

}
