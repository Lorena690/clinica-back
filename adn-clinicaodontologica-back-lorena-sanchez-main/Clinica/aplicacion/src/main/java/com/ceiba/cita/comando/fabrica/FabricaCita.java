package com.ceiba.cita.comando.fabrica;

import com.ceiba.cita.comando.ComandoCrearCita;
import com.ceiba.cita.comando.ComandoModificarCita;
import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.entidad.SolicitudCrearCita;
import com.ceiba.cita.modelo.entidad.SolicitudModificarCita;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FabricaCita {

    @Autowired
    DaoOdontologo daoOdontologo;

    @Autowired
    DaoPaciente daoPaciente;

    public SolicitudCrearCita crear(ComandoCrearCita comandoCrearCita) {
        return new SolicitudCrearCita(daoPaciente.obtenerPacientePorId(comandoCrearCita.getIdPaciente()), daoOdontologo.obtenerOdontologoPorId(comandoCrearCita.getIdOdontologo()), comandoCrearCita.getFechaYHora(), comandoCrearCita.getEspecialista());

    }


    public SolicitudModificarCita solicitudModificarCita(ComandoModificarCita comandoModificarCita) {
        return new SolicitudModificarCita(comandoModificarCita.getId(), daoOdontologo.obtenerOdontologoPorId(comandoModificarCita.getIdOdontologo()), comandoModificarCita.getFechaYHora(), comandoModificarCita.getValor(), comandoModificarCita.getEspecialista());

    }

    public ComandoModificarCita modificarCita(CitaDTO citaDTO) {
        return ComandoModificarCita.builder()
                .id(citaDTO.getId())
                .idOdontologo(citaDTO.getOdontologo().getId())
                .fechaYHora(citaDTO.getFechaYHora())
                .especialista(citaDTO.getEspecialista())
                .valor(citaDTO.getValor())
                .build();
    }
}
