package com.ceiba.odontologo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioOdontologoPostgres implements RepositorioOdontologo {

    @Autowired
    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "odontologo", value = "crearodontologo")
    private static String sqlCrear;

    @SqlStatement(namespace = "odontologo", value = "actualizarodontologo")
    private static String sqlActualizarOdontologo;

    @SqlStatement(namespace = "odontologo", value = "eliminarodontologo")
    private static String sqlEliminarOdontologo;

    @Override
    public Long guardar(Odontologo odontologo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", odontologo.getNombre());
        paramSource.addValue("apellido", odontologo.getApellido());
        paramSource.addValue("matricula", odontologo.getMatricula());

        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }

    @Override
    public SolicitudModificarOdontologo actualizar(Odontologo odontologo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", odontologo.getId());
        paramSource.addValue("nombre", odontologo.getNombre());
        paramSource.addValue("apellido", odontologo.getApellido());
        paramSource.addValue("matricula", odontologo.getMatricula());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarOdontologo, paramSource);
        return new SolicitudModificarOdontologo(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
    }

    @Override
    public void eliminarOdontologo(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarOdontologo, paramSource);
    }
}
