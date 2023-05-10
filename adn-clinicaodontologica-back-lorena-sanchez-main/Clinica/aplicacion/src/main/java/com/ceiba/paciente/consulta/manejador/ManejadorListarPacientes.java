package com.ceiba.paciente.consulta.manejador;

import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPacientes {
    @Autowired
    private DaoPaciente daoPaciente;

    public List<PacienteDTO> ejecutar() {
        return daoPaciente.listarPacientes();
    }
}
