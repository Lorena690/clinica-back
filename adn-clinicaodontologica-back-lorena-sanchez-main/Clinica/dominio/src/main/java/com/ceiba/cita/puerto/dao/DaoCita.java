package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.CitaDTO;

import java.util.List;

public interface DaoCita {

    List<CitaDTO> listarCitas();

    CitaDTO obtenerCitaPorId(Long id);
}
