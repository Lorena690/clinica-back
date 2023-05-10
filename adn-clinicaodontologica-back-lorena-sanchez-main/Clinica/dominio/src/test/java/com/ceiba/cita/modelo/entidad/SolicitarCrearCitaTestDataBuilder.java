package com.ceiba.cita.modelo.entidad;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;

import java.time.LocalDateTime;

public class SolicitarCrearCitaTestDataBuilder {

    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Double valor;
    private Boolean especialista;
    private EstadoCita estado;

    public SolicitarCrearCitaTestDataBuilder conCitaPorDefecto() {
        this.id = 5l;
        this.paciente = new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear();
        this.odontologo = new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear();
        this.fechaYHora = LocalDateTime.now();
        this.valor = 36000.0;
        this.especialista = false;
        this.estado = EstadoCita.CREADA;
        return this;
    }

    public SolicitudCrearCita crear() {
        return new SolicitudCrearCita(paciente, odontologo, fechaYHora, especialista);
    }

    public SolicitarCrearCitaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitarCrearCitaTestDataBuilder conPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
        return this;
    }

    public SolicitarCrearCitaTestDataBuilder conOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
        return this;
    }

    public SolicitarCrearCitaTestDataBuilder conFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public SolicitarCrearCitaTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public SolicitarCrearCitaTestDataBuilder conEspecialista(Boolean especialista) {
        this.especialista = especialista;
        return this;
    }

    public SolicitarCrearCitaTestDataBuilder conEstado(EstadoCita estadoCita) {
        this.estado = estadoCita;
        return this;
    }

}
