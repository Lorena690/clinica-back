package com.ceiba.cita.adaptador.dao;


import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCitaDTO implements RowMapper<CitaDTO>, MapperResult {
    @Autowired
    private DaoPaciente daoPaciente;

    @Autowired
    private DaoOdontologo daoOdontologo;

    @Override
    public CitaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var paciente = resultSet.getString("id_paciente");
        var odontologo = resultSet.getString("id_odontologo");
        var fechayhora = resultSet.getTimestamp("fechayhora").toLocalDateTime();
        var especialista = resultSet.getBoolean("especialista");
        var estado = EstadoCita.valueOf(resultSet.getString("estado"));
        var valor = resultSet.getDouble("valor");

        return new CitaDTO(id, daoPaciente.obtenerPacientePorId(Long.valueOf(paciente)), daoOdontologo.obtenerOdontologoPorId(Long.valueOf(odontologo)), fechayhora, especialista, valor, estado);
    }

}
