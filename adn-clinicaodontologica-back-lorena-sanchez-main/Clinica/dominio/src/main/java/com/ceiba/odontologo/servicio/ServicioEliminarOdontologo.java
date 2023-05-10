package com.ceiba.odontologo.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEliminarOdontologo {
    @Autowired
    private RepositorioOdontologo repositorioOdontologo;

    @Autowired
    private DaoOdontologo daoOdontologo;

    public ServicioEliminarOdontologo(RepositorioOdontologo repositorioOdontologo, DaoOdontologo daoOdontologo) {
        this.repositorioOdontologo = repositorioOdontologo;
        this.daoOdontologo = daoOdontologo;
    }

    public void ejecutarEliminarOdontologo(Long idOdontologo) {
        OdontologoDTO odontologoDTO = daoOdontologo.obtenerOdontologoPorId(idOdontologo);
        ValidadorArgumento.validarObligatorio(odontologoDTO, "El odontologo con id: " + idOdontologo + " no existe");
        repositorioOdontologo.eliminarOdontologo(idOdontologo);
    }
}
