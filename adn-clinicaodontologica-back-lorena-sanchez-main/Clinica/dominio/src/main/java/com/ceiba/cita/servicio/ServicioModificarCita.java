package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudModificarCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioModificarCita {
    @Autowired
    private RepositorioCita repositorioCita;

    @Autowired
    private DaoCita daoCita;

    public ServicioModificarCita(RepositorioCita repositorioCita, DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoCita = daoCita;
    }

    public CitaDTO ejecutarModificarCita(SolicitudModificarCita cita) {
        CitaDTO citaDTO = daoCita.obtenerCitaPorId(cita.getId());
        ValidadorArgumento.validarObligatorio(citaDTO, "la cita con id " + cita.getId() + " no existe");
        Cita cita1 = Cita.modificar(cita, citaDTO.getPaciente());
        return repositorioCita.actualizar(cita1);
    }
}
