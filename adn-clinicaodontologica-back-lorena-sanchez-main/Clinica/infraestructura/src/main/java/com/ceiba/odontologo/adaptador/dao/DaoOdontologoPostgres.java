package com.ceiba.odontologo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DaoOdontologoPostgres implements DaoOdontologo {

    @Autowired
    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    @Autowired
    private MapeoOdontologoDTO mapeoOdontologo;

    @SqlStatement(namespace = "odontologo", value = "listarodontologos")
    private static String sqlListarOdontologos;

    @SqlStatement(namespace = "odontologo", value = "obtenerodontologoporid")
    private static String sqlBuscarOdontologo;

    @Override
    public List<OdontologoDTO> listarOdontologos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarOdontologos, mapeoOdontologo);
    }

    @Override
    public OdontologoDTO obtenerOdontologoPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarOdontologo,
                        paramSource, mapeoOdontologo));
    }
}
