package com.ceiba.odontologo.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioModificarOdontologo {
    @Autowired
    private RepositorioOdontologo repositorioOdontologo;

    @Autowired
    private DaoOdontologo daoOdontologo;

    public ServicioModificarOdontologo(RepositorioOdontologo repositorioOdontologo, DaoOdontologo daoOdontologo) {
        this.repositorioOdontologo = repositorioOdontologo;
        this.daoOdontologo = daoOdontologo;
    }

    public SolicitudModificarOdontologo ejecutarModificarOdontologo(SolicitudModificarOdontologo odontologo) {
        OdontologoDTO odontologoDTO = daoOdontologo.obtenerOdontologoPorId(odontologo.getId());
        ValidadorArgumento.validarObligatorio(odontologoDTO, "El odontologo con id: " + odontologo.getId() + " no existe");
        Odontologo odontologo1 = Odontologo.modificar(odontologo);
        return repositorioOdontologo.actualizar(odontologo1);

    }
}
