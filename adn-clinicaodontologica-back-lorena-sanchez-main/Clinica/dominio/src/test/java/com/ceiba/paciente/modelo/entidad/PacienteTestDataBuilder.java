package com.ceiba.paciente.modelo.entidad;

public class PacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public PacienteTestDataBuilder conPacientePorDefecto() {
        this.id = 1l;
        this.nombre = "Lorena";
        this.apellido = "Ocampo";
        this.documento = "1234";
        this.estrato = "1";
        this.domicilio = "Calle 00 # 00 - 00";
        return this;
    }

    public Paciente crear() {
        return Paciente.crear(new SolicitudCrearPaciente(nombre, apellido, estrato, documento, domicilio));
    }

    public Paciente reconstruir() {
        return Paciente.reconstruir(id, nombre, apellido, estrato, documento, domicilio);
    }

    public Paciente modificar() {
        return Paciente.modificar(new SolicitudModificarPaciente(id, nombre, apellido, estrato, documento, domicilio));
    }

    public PacienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PacienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PacienteTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public PacienteTestDataBuilder conEstrato(String estrato) {
        this.estrato = estrato;
        return this;
    }

    public PacienteTestDataBuilder conDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public PacienteTestDataBuilder conDomicilio(String domicilio) {
        this.domicilio = domicilio;
        return this;
    }
}
