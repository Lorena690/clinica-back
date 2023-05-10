package com.ceiba.paciente.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DaoPacientePostgres implements DaoPaciente {

    @Autowired
    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    @Autowired
    private MapeoPacienteDTO mapeoPaciente;

    @SqlStatement(namespace = "paciente", value = "listarpacientes")
    private static String sqlListarPacientes;

    @SqlStatement(namespace = "paciente", value = "obtenerpacienteporid")
    private static String sqlBuscarPaciente;

    @Override
    public List<PacienteDTO> listarPacientes() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarPacientes, mapeoPaciente);
    }

    @Override
    public PacienteDTO obtenerPacientePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPaciente,
                        paramSource, mapeoPaciente));
    }
}
