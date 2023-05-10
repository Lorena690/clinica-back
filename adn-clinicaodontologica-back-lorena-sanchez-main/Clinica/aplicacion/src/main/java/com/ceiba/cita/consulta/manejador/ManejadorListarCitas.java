package com.ceiba.cita.consulta.manejador;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarCitas {
    @Autowired
    private DaoCita daoCita;

    public List<CitaDTO> ejecutar() {
        return daoCita.listarCitas();
    }
}
