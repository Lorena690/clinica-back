package com.ceiba.paciente.consulta.manejador;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarPaciente {
    @Autowired
    private DaoPaciente daoPaciente;

    public PacienteDTO ejecutar(Long id) {
        PacienteDTO pacienteDTO = daoPaciente.obtenerPacientePorId(id);
        ValidadorArgumento.validarSinDatos(pacienteDTO, "El paciente con id: " + id + " no existe");
        return pacienteDTO;
    }
}
