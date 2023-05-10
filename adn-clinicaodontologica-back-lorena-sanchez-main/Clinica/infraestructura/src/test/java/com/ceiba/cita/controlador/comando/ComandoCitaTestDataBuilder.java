package com.ceiba.cita.controlador.comando;


import com.ceiba.cita.comando.ComandoCrearCita;

import java.time.LocalDateTime;

public class ComandoCitaTestDataBuilder {

    private Long id_paciente;
    private Long id_odontologo;
    private LocalDateTime fechaYHora;
    private Boolean especialista;

    public ComandoCitaTestDataBuilder crearPorDefecto() {
        this.id_paciente = 1L;
        this.id_odontologo = 1L;
        this.fechaYHora = LocalDateTime.now();
        this.especialista = false;
        return this;
    }

    public ComandoCrearCita crear() {
        return new ComandoCrearCita(id_paciente, id_odontologo, fechaYHora, especialista);
    }

    public ComandoCitaTestDataBuilder conIdPaciente(Long id_paciente) {
        this.id_paciente = id_paciente;
        return this;
    }

    public ComandoCitaTestDataBuilder conIdOdontologo(Long id_odontologo) {
        this.id_odontologo = id_odontologo;
        return this;
    }
}
