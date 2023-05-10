package com.ceiba.odontologo.servicio;

import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudCrearOdontologo;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCrearOdontologo {
    @Autowired
    private RepositorioOdontologo repositorioOdontologo;

    @Autowired
    private DaoOdontologo daoOdontologo;

    public ServicioCrearOdontologo(RepositorioOdontologo repositorioOdontologo, DaoOdontologo daoOdontologo) {
        this.repositorioOdontologo = repositorioOdontologo;
        this.daoOdontologo = daoOdontologo;
    }

    public Long ejecutar(SolicitudCrearOdontologo odontologo) {
        Odontologo odontologo1 = Odontologo.crear(odontologo);
        return repositorioOdontologo.guardar(odontologo1);
    }
}
