package com.ceiba.paciente.modelo.entidad;

public class SolicitudModificarPacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public SolicitudModificarPacienteTestDataBuilder conPacientePorDefecto() {
        this.id = 1l;
        this.nombre = "Hector";
        this.apellido = "Ocampo";
        this.documento = "1234";
        this.estrato = "1";
        this.domicilio = "Calle 00 # 00 - 00";
        return this;
    }

    public SolicitudModificarPaciente crear() {
        return new SolicitudModificarPaciente(id, nombre, apellido, estrato, documento, domicilio);
    }

    public SolicitudModificarPacienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudModificarPacienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public SolicitudModificarPacienteTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public SolicitudModificarPacienteTestDataBuilder conEstrato(String estrato) {
        this.estrato = estrato;
        return this;
    }

    public SolicitudModificarPacienteTestDataBuilder conDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public SolicitudModificarPacienteTestDataBuilder conDomicilio(String domicilio) {
        this.domicilio = domicilio;
        return this;
    }
}
