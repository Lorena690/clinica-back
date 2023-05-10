package com.ceiba.cita.modelo.entidad;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.entidad.Paciente;

import java.time.LocalDateTime;

public final class Cita {
    public static final String EL_ODONTOLOGO_NO_EXISTE = "El odontologo no existe";
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDateTime fechaYHora;
    private Double valor;
    private Boolean especialista;
    private EstadoCita estado;

    private Cita(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora, Double valor, Boolean especialista, EstadoCita estado) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
        this.valor = valor;
        this.especialista = especialista;
        this.estado = estado;
    }

    private Cita(Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora, Double valor, Boolean especialista, EstadoCita estado) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
        this.valor = valor;
        this.especialista = especialista;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Odontologo getOdontologo() {
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

    public EstadoCita getEstado() {
        return estado;
    }

    public static Cita crear(SolicitudCrearCita cita, Double valor) {
        ValidadorArgumento.validarObligatorio(cita.getPaciente(), "El paciente no existe");
        ValidadorArgumento.validarObligatorio(cita.getOdontologo(), EL_ODONTOLOGO_NO_EXISTE);
        ValidadorArgumento.validarObligatorio(cita.getFechaYHora(), "La fecha y hora son requeridos para crear una cita");
        ValidadorArgumento.validarObligatorio(cita.getEspecialista(), "El especialista es requerido para crear una cita");
        ValidadorArgumento.validarFinDeSemana(cita.getFechaYHora(), "Los fines de semana no se asignan citas");
        return new Cita(cita.getPaciente().convertirAPaciente(), cita.getOdontologo().convertirAOdontologo(), cita.getFechaYHora(), valor, cita.getEspecialista(), EstadoCita.CREADA);
    }

    public static Cita modificar(SolicitudModificarCita cita, PacienteDTO paciente) {
        ValidadorArgumento.validarObligatorio(cita.getId(), "El id es requerido para modificar una cita");
        ValidadorArgumento.validarObligatorio(cita.getOdontologo(), EL_ODONTOLOGO_NO_EXISTE);
        ValidadorArgumento.validarObligatorio(cita.getFechaYHora(), "La fecha y hora son requeridos para modificar una cita");
        ValidadorArgumento.validarObligatorio(cita.getEspecialista(), "El especialista es requerido para modificar una cita");
        ValidadorArgumento.validarObligatorio(cita.getValor(), "El valor es requerido para modificar una cita");
        ValidadorArgumento.validarFinDeSemana(cita.getFechaYHora(), "Los fines de semana no se asignan citas");
        return new Cita(cita.getId(), paciente.convertirAPaciente(), cita.getOdontologo().convertirAOdontologo(), cita.getFechaYHora(), cita.getValor(), cita.getEspecialista(), EstadoCita.MODIFICADA);
    }

    public static Cita cancelar(CitaDTO cita) {
        return new Cita(cita.getId(), cita.getPaciente().convertirAPaciente(), cita.getOdontologo().convertirAOdontologo(), cita.getFechaYHora(), cita.getValor(), cita.getEspecialista(), EstadoCita.CANCELADA);
    }

    public static Cita crearConFecha(SolicitudCrearCita cita, Double valor, LocalDateTime fecha) {
        return new Cita(cita.getPaciente().convertirAPaciente(), cita.getOdontologo().convertirAOdontologo(), fecha, valor, cita.getEspecialista(), EstadoCita.CREADA);
    }

    public static Cita reconstruir(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora, Double valor, Boolean especialista, EstadoCita estado) {
        ValidadorArgumento.validarObligatorio(id, "El id es requerido para crear una cita");
        ValidadorArgumento.validarObligatorio(paciente, "El paciente no existe");
        ValidadorArgumento.validarObligatorio(odontologo, EL_ODONTOLOGO_NO_EXISTE);
        ValidadorArgumento.validarObligatorio(fechaYHora, "La fecha y hora son requeridos para crear una cita");
        ValidadorArgumento.validarObligatorio(especialista, "El especialista es requerido para crear una cita");
        ValidadorArgumento.validarObligatorio(valor, "El valor es requerido para crear una cita");
        ValidadorArgumento.validarObligatorio(estado, "El estado es requerido para crear una cita");

        return new Cita(id, paciente, odontologo, fechaYHora, valor, especialista, estado);
    }
}
