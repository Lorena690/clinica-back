package com.ceiba.odontologo.comando.fabrica;

import com.ceiba.odontologo.comando.ComandoCrearOdontologo;
import com.ceiba.odontologo.comando.ComandoModificarOdontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudCrearOdontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;
import org.springframework.stereotype.Component;


@Component
public class FabricaOdontologo {

    public SolicitudCrearOdontologo crear(ComandoCrearOdontologo comandoCrearOdontologo) {
        return new SolicitudCrearOdontologo(comandoCrearOdontologo.getNombre(), comandoCrearOdontologo.getApellido(), comandoCrearOdontologo.getMatricula());
    }

    public SolicitudModificarOdontologo solicitudModificarOdontologo(ComandoModificarOdontologo comandoModificarOdontologo) {
        return new SolicitudModificarOdontologo(comandoModificarOdontologo.getId(), comandoModificarOdontologo.getNombre(), comandoModificarOdontologo.getApellido(), comandoModificarOdontologo.getMatricula());
    }

    public ComandoModificarOdontologo modificarOdontologo(SolicitudModificarOdontologo solicitudModificarOdontologo) {
        return ComandoModificarOdontologo.builder()
                .id(solicitudModificarOdontologo.getId())
                .nombre(solicitudModificarOdontologo.getNombre())
                .apellido(solicitudModificarOdontologo.getApellido())
                .matricula(solicitudModificarOdontologo.getMatricula())

                .build();
    }
}
