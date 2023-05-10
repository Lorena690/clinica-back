package com.ceiba.cita.modelo.entidad;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;

import java.time.LocalDateTime;

public class SolicitudModificarCitaTestDataBuilder {

    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Double valor;
    private Boolean especialista;
    private EstadoCita estado;

    public SolicitudModificarCitaTestDataBuilder conCitaPorDefecto() {
        this.id = 5l;
        this.paciente = new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear();
        this.odontologo = new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear();
        this.fechaYHora = LocalDateTime.now();
        this.valor = 36000.0;
        this.especialista = false;
        this.estado = EstadoCita.CREADA;
        return this;
    }

    public SolicitudModificarCita crear() {
        return new SolicitudModificarCita(id, odontologo, fechaYHora, valor, especialista);
    }

    public SolicitudModificarCitaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudModificarCitaTestDataBuilder conPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
        return this;
    }

    public SolicitudModificarCitaTestDataBuilder conOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
        return this;
    }

    public SolicitudModificarCitaTestDataBuilder conFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public SolicitudModificarCitaTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public SolicitudModificarCitaTestDataBuilder conEspecialista(Boolean especialista) {
        this.especialista = especialista;
        return this;
    }

    public SolicitudModificarCitaTestDataBuilder conEstado(EstadoCita estadoCita) {
        this.estado = estadoCita;
        return this;
    }

}
