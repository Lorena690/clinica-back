package com.ceiba.odontologo.puerto.dao;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;

import java.util.List;

public interface DaoOdontologo {

    List<OdontologoDTO> listarOdontologos();

    OdontologoDTO obtenerOdontologoPorId(Long id);
}
