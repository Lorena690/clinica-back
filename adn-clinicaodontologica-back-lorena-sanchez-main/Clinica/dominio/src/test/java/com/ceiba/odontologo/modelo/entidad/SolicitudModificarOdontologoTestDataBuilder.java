package com.ceiba.odontologo.modelo.entidad;

public class SolicitudModificarOdontologoTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public SolicitudModificarOdontologoTestDataBuilder conPacientePorDefecto() {
        this.id = 1l;
        this.nombre = "Lorena";
        this.apellido = "Sanchez";
        this.matricula = "1234";
        return this;
    }

    public SolicitudModificarOdontologo crear() {
        return new SolicitudModificarOdontologo(id, nombre, apellido, matricula);
    }


    public SolicitudModificarOdontologoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudModificarOdontologoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public SolicitudModificarOdontologoTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public SolicitudModificarOdontologoTestDataBuilder conMatricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

}
