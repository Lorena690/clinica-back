package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudCrearCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
public class ServicioCrearCita {
    @Autowired
    private RepositorioCita repositorioCita;

    @Autowired
    private DaoCita daoCita;

    public ServicioCrearCita(RepositorioCita repositorioCita, DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoCita = daoCita;
    }

    public Long ejecutar(SolicitudCrearCita cita) {
        Cita cita1 = Cita.crear(cita, calcularValorCita(cita));
        if (cita1.getEspecialista()) {
            LocalDateTime fechaCitaControl = sumarDias(cita.getFechaYHora(), 15);
            Cita cita2 = Cita.crearConFecha(cita, calcularValorCita(cita), fechaCitaControl);
            repositorioCita.guardar(cita2);
        }
        return repositorioCita.guardar(cita1);
    }

    public Double calcularValorCita(SolicitudCrearCita cita) {
        Double valorBaseCita = 100000.;
        String estrato = cita.getPaciente() == null ? "" : cita.getPaciente().getEstrato();
        switch (estrato) {
            case ("1"):
                return valorBaseCita * 0.6;
            case ("2"):
                return valorBaseCita * 0.7;
            case ("3"):
                return valorBaseCita * 0.8;
            default:
                return valorBaseCita;
        }
    }

    public LocalDateTime sumarDias(LocalDateTime dateTime, Integer dias) {
        LocalDateTime resultado = dateTime;
        Integer diasAgregados = 0;
        while (diasAgregados < dias) {
            resultado = resultado.plusDays(1);
            if (!(resultado.getDayOfWeek() == DayOfWeek.SATURDAY || resultado.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diasAgregados;
            }
        }
        return resultado;
    }

}
