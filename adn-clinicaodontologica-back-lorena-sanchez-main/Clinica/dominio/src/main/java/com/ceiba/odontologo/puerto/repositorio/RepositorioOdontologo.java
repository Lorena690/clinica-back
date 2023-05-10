package com.ceiba.odontologo.puerto.repositorio;

import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;

public interface RepositorioOdontologo {
    Long guardar(Odontologo odontologo);

    SolicitudModificarOdontologo actualizar(Odontologo odontologo);

    void eliminarOdontologo(Long id);
}
