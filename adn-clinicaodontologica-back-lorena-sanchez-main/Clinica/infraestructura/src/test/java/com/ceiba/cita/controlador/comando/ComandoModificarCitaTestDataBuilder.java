package com.ceiba.cita.controlador.comando;


import com.ceiba.cita.comando.ComandoModificarCita;

import java.time.LocalDateTime;

public class ComandoModificarCitaTestDataBuilder {

    private Long id;
    private Long id_odontologo;
    private LocalDateTime fechaYHora;
    private Boolean especialista;

    private Double valor;

    public ComandoModificarCitaTestDataBuilder crearPorDefecto() {
        this.id = 1L;
        this.id_odontologo = 1L;
        this.fechaYHora = LocalDateTime.now();
        this.especialista = false;
        this.valor = 50000.00;
        return this;
    }

    public ComandoModificarCita crear() {
        return new ComandoModificarCita(id, id_odontologo, fechaYHora, especialista, valor);
    }

    public ComandoModificarCitaTestDataBuilder conId(Long valor) {
        this.id = valor;
        return this;
    }

    public ComandoModificarCitaTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }
}
