package com.ceiba.odontologo.modelo.entidad;

public class OdontologoTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoTestDataBuilder conOdontologoPorDefecto() {
        this.id = 1l;
        this.nombre = "Lorena";
        this.apellido = "Sanchez";
        this.matricula = "1234";
        return this;
    }

    public Odontologo crear() {
        return Odontologo.crear(new SolicitudCrearOdontologo(nombre, apellido, matricula));
    }

    public Odontologo reconstruir() {
        return Odontologo.reconstruir(id, nombre, apellido, matricula);
    }

    public Odontologo modificar() {
        return Odontologo.modificar(new SolicitudModificarOdontologo(id, nombre, apellido, matricula));
    }

    public OdontologoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public OdontologoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public OdontologoTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public OdontologoTestDataBuilder conMatricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

}
