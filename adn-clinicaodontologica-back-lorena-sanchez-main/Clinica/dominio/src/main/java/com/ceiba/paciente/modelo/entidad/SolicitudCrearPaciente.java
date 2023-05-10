package com.ceiba.paciente.modelo.entidad;

public class SolicitudCrearPaciente {
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public SolicitudCrearPaciente(String nombre, String apellido, String estrato, String documento, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estrato = estrato;
        this.documento = documento;
        this.domicilio = domicilio;
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
}
