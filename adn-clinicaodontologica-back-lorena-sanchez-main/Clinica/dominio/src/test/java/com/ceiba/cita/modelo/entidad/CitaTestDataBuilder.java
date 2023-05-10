package com.ceiba.cita.modelo.entidad;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;

import java.time.LocalDateTime;

public class CitaTestDataBuilder {

    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fechaYHora;
    private Double valor;
    private Boolean especialista;
    private EstadoCita estado;

    public CitaTestDataBuilder conCitaPorDefecto() {
        this.id = 5l;
        this.paciente = new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear();
        this.odontologo = new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear();
        this.fechaYHora = LocalDateTime.now();
        this.valor = 36000.0;
        this.especialista = false;
        this.estado = EstadoCita.CREADA;
        return this;
    }

    public Cita crear() {
        return Cita.crear(new SolicitudCrearCita(paciente, odontologo, fechaYHora, especialista), valor);
    }

    public Cita reconstruir() {
        return Cita.reconstruir(id, paciente == null ? null : paciente.convertirAPaciente(), odontologo == null ? null : odontologo.convertirAOdontologo(), fechaYHora, valor, especialista, estado);
    }

    public Cita crearConFecha() {
        return Cita.crearConFecha(new SolicitudCrearCita(paciente, odontologo, fechaYHora, especialista), valor, fechaYHora);
    }

    public Cita modificar() {
        return Cita.modificar(new SolicitudModificarCita(id, odontologo, fechaYHora, valor, especialista), paciente);
    }

    public CitaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public CitaTestDataBuilder conPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
        return this;
    }

    public CitaTestDataBuilder conOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
        return this;
    }

    public CitaTestDataBuilder conFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public CitaTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public CitaTestDataBuilder conEspecialista(Boolean especialista) {
        this.especialista = especialista;
        return this;
    }

    public CitaTestDataBuilder conEstado(EstadoCita estadoCita) {
        this.estado = estadoCita;
        return this;
    }

}
