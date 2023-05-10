package com.ceiba.paciente.servicio;

import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.SolicitudCrearPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCrearPaciente {
    @Autowired
    private RepositorioPaciente repositorioPaciente;

    @Autowired
    private DaoPaciente daoPaciente;

    public ServicioCrearPaciente(RepositorioPaciente repositorioPaciente, DaoPaciente daoPaciente) {
        this.repositorioPaciente = repositorioPaciente;
        this.daoPaciente = daoPaciente;
    }

    public Long ejecutar(SolicitudCrearPaciente paciente) {
        Paciente paciente1 = Paciente.crear(paciente);
        return repositorioPaciente.guardar(paciente1);
    }
}
