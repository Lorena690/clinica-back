package com.ceiba.cita.modelo.entidad;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;

import java.time.LocalDateTime;


public class SolicitudModificarCita {
    private Long id;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Double valor;
    private Boolean especialista;

    public SolicitudModificarCita(Long id, OdontologoDTO odontologo, LocalDateTime fechaYHora, Double valor, Boolean especialista) {
        this.id = id;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
        this.valor = valor;
        this.especialista = especialista;
    }

    public Long getId() {
        return id;
    }

    public OdontologoDTO getOdontologo() {
        return odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public Double getValor() {
        return valor;
    }

    public Boolean getEspecialista() {
        return especialista;
    }
}
