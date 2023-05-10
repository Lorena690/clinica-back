package com.ceiba.cita.consulta.manejador;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.dominio.ValidadorArgumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarCita {
    @Autowired
    private DaoCita daoCita;

    public CitaDTO ejecutar(Long id) {
        CitaDTO citaDTO = daoCita.obtenerCitaPorId(id);
        ValidadorArgumento.validarSinDatos(citaDTO, "La cita con id: " + id + " no existe");
        return citaDTO;
    }
}
