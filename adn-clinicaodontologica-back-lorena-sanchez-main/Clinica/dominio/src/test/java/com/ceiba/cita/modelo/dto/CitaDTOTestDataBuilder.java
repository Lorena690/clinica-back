package com.ceiba.cita.modelo.dto;

import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;

import java.time.LocalDateTime;

public class CitaDTOTestDataBuilder {

    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Double valor;
    private Boolean especialista;
    private EstadoCita estado;

    public CitaDTOTestDataBuilder conCitaPorDefecto() {
        this.id = 5l;
        this.paciente = new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear();
        this.odontologo = new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear();
        this.fechaYHora = LocalDateTime.now();
        this.valor = 36000.0;
        this.especialista = false;
        this.estado = EstadoCita.CREADA;
        return this;
    }

    public CitaDTO crear() {
        return new CitaDTO(id, paciente, odontologo, fechaYHora, especialista, valor, estado);
    }

    public CitaDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public CitaDTOTestDataBuilder conPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
        return this;
    }

    public CitaDTOTestDataBuilder conOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
        return this;
    }

    public CitaDTOTestDataBuilder conFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public CitaDTOTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public CitaDTOTestDataBuilder conEspecialista(Boolean especialista) {
        this.especialista = especialista;
        return this;
    }

    public CitaDTOTestDataBuilder conEstado(EstadoCita estadoCita) {
        this.estado = estadoCita;
        return this;
    }

}
