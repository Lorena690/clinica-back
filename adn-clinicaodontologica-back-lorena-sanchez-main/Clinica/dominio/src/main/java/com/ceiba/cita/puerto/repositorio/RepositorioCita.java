package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;

public interface RepositorioCita {
    Long guardar(Cita cita);

    CitaDTO actualizar(Cita cita);

    void cancelarCita(Cita cita);
}
