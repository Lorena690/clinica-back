package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DaoCitaPostgres implements DaoCita {

    @Autowired
    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    @Autowired
    private MapeoCitaDTO mapeoCita;

    @SqlStatement(namespace = "cita", value = "listarcitas")
    private static String sqlListarCitas;

    @SqlStatement(namespace = "cita", value = "obtenercitaporid")
    private static String sqlBuscarCita;

    @Override
    public List<CitaDTO> listarCitas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarCitas, mapeoCita);
    }

    @Override
    public CitaDTO obtenerCitaPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarCita,
                        paramSource, mapeoCita));
    }
}
