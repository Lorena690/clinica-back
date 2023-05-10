package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCancelarCita {
    @Autowired
    private RepositorioCita repositorioCita;

    @Autowired
    private DaoCita daoCita;

    public ServicioCancelarCita(RepositorioCita repositorioCita, DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoCita = daoCita;
    }

    public void ejecutarCancelarCita(Long idcita) {
        CitaDTO citaDTO = daoCita.obtenerCitaPorId(idcita);
        ValidadorArgumento.validarObligatorio(citaDTO, "La cita con id " + idcita + " no existe");
        Cita cita1 = Cita.cancelar(citaDTO);
        repositorioCita.cancelarCita(cita1);
    }
}
