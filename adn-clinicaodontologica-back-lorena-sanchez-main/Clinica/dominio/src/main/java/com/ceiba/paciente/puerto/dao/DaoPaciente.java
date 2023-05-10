package com.ceiba.paciente.puerto.dao;

import com.ceiba.paciente.modelo.dto.PacienteDTO;

import java.util.List;

public interface DaoPaciente {

    List<PacienteDTO> listarPacientes();

    PacienteDTO obtenerPacientePorId(Long id);
}
