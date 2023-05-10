package com.ceiba.cita.modelo.entidad;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTO;

import java.time.LocalDateTime;

public class SolicitudCrearCita {

    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Boolean especialista;

    public SolicitudCrearCita(PacienteDTO paciente, OdontologoDTO odontologo, LocalDateTime fechaYHora, Boolean especialista) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
        this.especialista = especialista;
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
}
