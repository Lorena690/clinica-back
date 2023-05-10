package com.ceiba.paciente.modelo.entidad;

public class SolicitudCrearPacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public SolicitudCrearPacienteTestDataBuilder conPacientePorDefecto() {
        this.id = 1l;
        this.nombre = "Hector";
        this.apellido = "Ocampo";
        this.documento = "1234";
        this.estrato = "1";
        this.domicilio = "Calle 00 # 00 - 00";
        return this;
    }

    public SolicitudCrearPaciente crear() {
        return new SolicitudCrearPaciente(nombre, apellido, estrato, documento, domicilio);
    }

    public SolicitudCrearPacienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudCrearPacienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public SolicitudCrearPacienteTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public SolicitudCrearPacienteTestDataBuilder conEstrato(String estrato) {
        this.estrato = estrato;
        return this;
    }

    public SolicitudCrearPacienteTestDataBuilder conDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public SolicitudCrearPacienteTestDataBuilder conDomicilio(String domicilio) {
        this.domicilio = domicilio;
        return this;
    }
}
