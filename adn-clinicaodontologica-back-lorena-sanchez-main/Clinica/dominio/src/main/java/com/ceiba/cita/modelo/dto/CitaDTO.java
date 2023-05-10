package com.ceiba.cita.modelo.dto;

import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTO;

import java.time.LocalDateTime;


public class CitaDTO {
    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Boolean especialista;
    private Double valor;
    private EstadoCita estado;

    public CitaDTO(Long id, PacienteDTO paciente, OdontologoDTO odontologo, LocalDateTime fechaYHora, Boolean especialista, Double valor, EstadoCita estado) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
        this.especialista = especialista;
        this.valor = valor;
        this.estado = estado;
    }


    public Long getId() {
        return id;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public OdontologoDTO getOdontologo() {
        return odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public Boolean getEspecialista() {
        return especialista;
    }

    public Double getValor() {
        return valor;
    }

    public EstadoCita getEstado() {
        return estado;
    }
}
