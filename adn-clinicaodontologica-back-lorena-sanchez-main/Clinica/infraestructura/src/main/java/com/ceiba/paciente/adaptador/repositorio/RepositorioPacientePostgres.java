package com.ceiba.paciente.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPacientePostgres implements RepositorioPaciente {

    @Autowired
    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "paciente", value = "crearpaciente")
    private static String sqlCrear;

    @SqlStatement(namespace = "paciente", value = "actualizarpaciente")
    private static String sqlActualizarPaciente;

    @SqlStatement(namespace = "paciente", value = "eliminarpaciente")
    private static String sqlEliminarPaciente;

    @Override
    public Long guardar(Paciente paciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", paciente.getNombre());
        paramSource.addValue("apellido", paciente.getApellido());
        paramSource.addValue("estrato", paciente.getEstrato());
        paramSource.addValue("documento", paciente.getDocumento());
        paramSource.addValue("domicilio", paciente.getDomicilio());

        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }

    @Override
    public SolicitudModificarPaciente actualizar(Paciente paciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", paciente.getId());
        paramSource.addValue("nombre", paciente.getNombre());
        paramSource.addValue("apellido", paciente.getApellido());
        paramSource.addValue("estrato", paciente.getEstrato());
        paramSource.addValue("documento", paciente.getDocumento());
        paramSource.addValue("domicilio", paciente.getDomicilio());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarPaciente, paramSource);
        return new SolicitudModificarPaciente(paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getEstrato(), paciente.getDocumento(), paciente.getDomicilio());
    }

    @Override
    public void eliminarPaciente(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarPaciente, paramSource);
    }
}
