package com.ceiba.paciente.modelo.dto;

import com.ceiba.paciente.modelo.entidad.Paciente;

public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public PacienteDTO(Long id, String nombre, String apellido, String estrato, String documento, String domicilio) {
        this.id = id;
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

    public Paciente convertirAPaciente() {
        return Paciente.reconstruir(id, nombre, apellido, estrato, documento, domicilio);
    }
}
