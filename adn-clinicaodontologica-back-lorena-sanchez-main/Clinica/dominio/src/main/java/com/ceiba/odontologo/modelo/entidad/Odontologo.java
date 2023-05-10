package com.ceiba.odontologo.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public final class Odontologo {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    private Odontologo(Long id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    private Odontologo(String nombre, String apellido, String matricula) {
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

    public static Odontologo crear(SolicitudCrearOdontologo odontologo) {
        ValidadorArgumento.validarObligatorio(odontologo.getNombre(), "El nombre es requerido para crear un odontologo");
        ValidadorArgumento.validarObligatorio(odontologo.getApellido(), "El apellido es requerido para crear un odontologo");
        ValidadorArgumento.validarObligatorio(odontologo.getMatricula(), "La matricula es requerida para crear un odontologo");
        return new Odontologo(odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());

    }

    public static Odontologo modificar(SolicitudModificarOdontologo odontologo) {
        ValidadorArgumento.validarObligatorio(odontologo.getId(), "El Id es requerido para modificar un odontologo");
        return new Odontologo(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
    }

    public static Odontologo reconstruir(Long id, String nombre, String apellido, String matricula) {
        ValidadorArgumento.validarObligatorio(id, "El Id es requerido para crear un odontologo");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre es requerido para crear un odontologo");
        ValidadorArgumento.validarObligatorio(apellido, "El apellido es requerido para crear un odontologo");
        ValidadorArgumento.validarObligatorio(matricula, "La matricula es requerida para crear un odontologo");

        return new Odontologo(id, nombre, apellido, matricula);
    }
}
