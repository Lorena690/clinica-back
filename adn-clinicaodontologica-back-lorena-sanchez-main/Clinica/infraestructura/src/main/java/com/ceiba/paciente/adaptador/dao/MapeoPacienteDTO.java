package com.ceiba.paciente.adaptador.dao;


import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPacienteDTO implements RowMapper<PacienteDTO>, MapperResult {

    @Override
    public PacienteDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var apellido = resultSet.getString("apellido");
        var estrato = resultSet.getString("estrato");
        var documento = resultSet.getString("documento");
        var domicilio = resultSet.getString("domicilio");

        return new PacienteDTO(id, nombre, apellido, estrato, documento, domicilio);
    }

}
