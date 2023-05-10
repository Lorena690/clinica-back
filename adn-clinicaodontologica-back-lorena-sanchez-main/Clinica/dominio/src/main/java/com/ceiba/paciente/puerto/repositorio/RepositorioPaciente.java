package com.ceiba.paciente.puerto.repositorio;

import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;

public interface RepositorioPaciente {
    Long guardar(Paciente paciente);

    SolicitudModificarPaciente actualizar(Paciente paciente);

    void eliminarPaciente(Long id);
}
