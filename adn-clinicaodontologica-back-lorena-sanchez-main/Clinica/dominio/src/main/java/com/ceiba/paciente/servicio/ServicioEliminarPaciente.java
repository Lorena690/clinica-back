package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEliminarPaciente {
    @Autowired
    private RepositorioPaciente repositorioPaciente;

    @Autowired
    private DaoPaciente daoPaciente;

    public ServicioEliminarPaciente(RepositorioPaciente repositorioPaciente, DaoPaciente daoPaciente) {
        this.repositorioPaciente = repositorioPaciente;
        this.daoPaciente = daoPaciente;
    }

    public void ejecutarEliminarPaciente(Long idPaciente) {
        PacienteDTO pacienteDTO = daoPaciente.obtenerPacientePorId(idPaciente);
        ValidadorArgumento.validarObligatorio(pacienteDTO, "El paciente con id: " + idPaciente + " no existe");
        repositorioPaciente.eliminarPaciente(idPaciente);
    }
}
