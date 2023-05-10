package com.ceiba.odontologo.consulta.manejador;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarOdontologo {
    @Autowired
    private DaoOdontologo daoOdontologo;

    public OdontologoDTO ejecutar(Long id) {
        OdontologoDTO odontologoDTO = daoOdontologo.obtenerOdontologoPorId(id);
        ValidadorArgumento.validarSinDatos(odontologoDTO, "El odontologo con id: " + id + " no existe");
        return odontologoDTO;
    }
}
