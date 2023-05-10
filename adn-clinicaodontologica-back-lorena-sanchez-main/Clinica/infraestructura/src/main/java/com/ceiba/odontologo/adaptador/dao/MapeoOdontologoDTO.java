package com.ceiba.odontologo.adaptador.dao;


import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoOdontologoDTO implements RowMapper<OdontologoDTO>, MapperResult {

    @Override
    public OdontologoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var apellido = resultSet.getString("apellido");
        var matricula = resultSet.getString("matricula");

        return new OdontologoDTO(id, nombre, apellido, matricula);
    }

}
