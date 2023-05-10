package com.ceiba.paciente.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public final class Paciente {
    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    private Paciente(Long id, String nombre, String apellido, String estrato, String documento, String domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estrato = estrato;
        this.documento = documento;
        this.domicilio = domicilio;
    }

    private Paciente(String nombre, String apellido, String estrato, String documento, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estrato = estrato;
        this.documento = documento;
        this.domicilio = domicilio;
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

    public String getEstrato() {
        return estrato;
    }

    public String getDocumento() {
        return documento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public static Paciente crear(SolicitudCrearPaciente paciente) {
        ValidadorArgumento.validarObligatorio(paciente.getNombre(), "El nombre es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(paciente.getEstrato(), "El estrato es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(paciente.getApellido(), "El apellido es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(paciente.getDocumento(), "El documento es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(paciente.getDomicilio(), "El domicilio es requerido para crear un paciente");
        return new Paciente(paciente.getNombre(), paciente.getApellido(), paciente.getEstrato(), paciente.getDocumento(), paciente.getDomicilio());
    }

    public static Paciente modificar(SolicitudModificarPaciente paciente) {
        ValidadorArgumento.validarObligatorio(paciente.getId(), "El Id es requerido para modificar un paciente");
        return new Paciente(paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getEstrato(), paciente.getDocumento(), paciente.getDomicilio());
    }

    public static Paciente reconstruir(Long id, String nombre, String apellido, String estrato, String documento, String domicilio) {
        ValidadorArgumento.validarObligatorio(id, "El Id es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(apellido, "El apellido es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(estrato, "El estrato es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(documento, "El documento es requerido para crear un paciente");
        ValidadorArgumento.validarObligatorio(domicilio, "El domicilio es requerido para crear un paciente");

        return new Paciente(id, nombre, apellido, estrato, documento, domicilio);
    }
}
