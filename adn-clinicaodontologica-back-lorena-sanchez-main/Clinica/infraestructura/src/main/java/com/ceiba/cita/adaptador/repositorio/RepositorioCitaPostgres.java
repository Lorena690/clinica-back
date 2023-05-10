package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCitaPostgres implements RepositorioCita {

    public static final String ESTADO = "estado";
    @Autowired
    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @Autowired
    private DaoPaciente daoPaciente;

    @Autowired
    private DaoOdontologo daoOdontologo;

    @SqlStatement(namespace = "cita", value = "crearcita")
    private static String sqlCrear;

    @SqlStatement(namespace = "cita", value = "actualizarcita")
    private static String sqlActualizarCita;

    @SqlStatement(namespace = "cita", value = "cancelarcita")
    private static String sqlCancelarCita;

    @Override
    public Long guardar(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idpaciente", cita.getPaciente().getId());
        paramSource.addValue("idodontologo", cita.getOdontologo().getId());
        paramSource.addValue("fechayhora", cita.getFechaYHora());
        paramSource.addValue("especialista", cita.getEspecialista());
        paramSource.addValue(ESTADO, cita.getEstado().name());
        paramSource.addValue("valor", cita.getValor());

        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }

    @Override
    public CitaDTO actualizar(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", cita.getId());
        paramSource.addValue("idodontologo", cita.getOdontologo().getId());
        paramSource.addValue("fechayhora", cita.getFechaYHora());
        paramSource.addValue("especialista", cita.getEspecialista());
        paramSource.addValue(ESTADO, cita.getEstado().name());
        paramSource.addValue("valor", cita.getValor());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarCita, paramSource);
        return new CitaDTO(cita.getId(), daoPaciente.obtenerPacientePorId(cita.getPaciente().getId()), daoOdontologo.obtenerOdontologoPorId(cita.getOdontologo().getId()), cita.getFechaYHora(), cita.getEspecialista(), cita.getValor(), cita.getEstado());
    }

    @Override
    public void cancelarCita(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", cita.getId());
        paramSource.addValue(ESTADO, cita.getEstado().name());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCancelarCita, paramSource);
    }
}
