package com.ceiba.odontologo.consulta.manejador;

import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarOdontologos {
    @Autowired
    private DaoOdontologo daoOdontologo;

    public List<OdontologoDTO> ejecutar() {
        return daoOdontologo.listarOdontologos();
    }
}
