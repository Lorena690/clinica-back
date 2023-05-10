package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioModificarPaciente {
    @Autowired
    private RepositorioPaciente repositorioPaciente;

    @Autowired
    private DaoPaciente daoPaciente;

    public ServicioModificarPaciente(RepositorioPaciente repositorioPaciente, DaoPaciente daoPaciente) {
        this.repositorioPaciente = repositorioPaciente;
        this.daoPaciente = daoPaciente;
    }

    public SolicitudModificarPaciente ejecutarModificarPaciente(SolicitudModificarPaciente paciente) {
        PacienteDTO pacienteDTO = daoPaciente.obtenerPacientePorId(paciente.getId());
        ValidadorArgumento.validarObligatorio(pacienteDTO, "El paciente con id: " + paciente.getId() + " no existe");

        Paciente paciente1 = Paciente.modificar(paciente);
        return repositorioPaciente.actualizar(paciente1);
    }
}
