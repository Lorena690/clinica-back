package com.ceiba.paciente.modelo.dto;

public class PacienteDTOTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public PacienteDTOTestDataBuilder conPacientePorDefecto() {
        this.id = 1l;
        this.nombre = "Lore";
        this.apellido = "Sanchez";
        this.documento = "1234";
        this.estrato = "1";
        this.domicilio = "Calle 00 # 00 - 00";
        return this;
    }

    public PacienteDTO crear() {
        return new PacienteDTO(id, nombre, apellido, estrato, documento, domicilio);
    }

    public PacienteDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PacienteDTOTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PacienteDTOTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public PacienteDTOTestDataBuilder conEstrato(String estrato) {
        this.estrato = estrato;
        return this;
    }

    public PacienteDTOTestDataBuilder conDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public PacienteDTOTestDataBuilder conDomicilio(String domicilio) {
        this.domicilio = domicilio;
        return this;
    }
}
