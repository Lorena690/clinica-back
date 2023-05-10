package com.ceiba.paciente.comando.fabrica;

import com.ceiba.paciente.comando.ComandoCrearPaciente;
import com.ceiba.paciente.comando.ComandoModificarPaciente;
import com.ceiba.paciente.modelo.entidad.SolicitudCrearPaciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;
import org.springframework.stereotype.Component;


@Component
public class FabricaPaciente {

    public SolicitudCrearPaciente crear(ComandoCrearPaciente comandoCrearPaciente) {
        return new SolicitudCrearPaciente(comandoCrearPaciente.getNombre(), comandoCrearPaciente.getApellido(), comandoCrearPaciente.getEstrato(), comandoCrearPaciente.getDocumento(), comandoCrearPaciente.getDomicilio());
    }

    public SolicitudModificarPaciente solicitudModificarPaciente(ComandoModificarPaciente comandoModificarPaciente) {
        return new SolicitudModificarPaciente(
                comandoModificarPaciente.getId(),
                comandoModificarPaciente.getNombre(),
                comandoModificarPaciente.getApellido(),
                comandoModificarPaciente.getEstrato(),
                comandoModificarPaciente.getDocumento(),
                comandoModificarPaciente.getDomicilio()
        );
    }

    public ComandoModificarPaciente modificarPaciente(SolicitudModificarPaciente solicitudModificarPaciente) {
        return ComandoModificarPaciente.builder()
                .id(solicitudModificarPaciente.getId())
                .nombre(solicitudModificarPaciente.getNombre())
                .apellido(solicitudModificarPaciente.getApellido())
                .documento(solicitudModificarPaciente.getDocumento())
                .estrato(solicitudModificarPaciente.getEstrato())
                .domicilio(solicitudModificarPaciente.getDomicilio())
                .build();
    }
}
