package com.ceiba.odontologo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.odontologo.comando.ComandoModificarOdontologo;
import com.ceiba.odontologo.comando.fabrica.FabricaOdontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;
import com.ceiba.odontologo.servicio.ServicioModificarOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorModificarOdontologo implements ManejadorComandoRespuesta<ComandoModificarOdontologo, ComandoRespuesta<ComandoModificarOdontologo>> {

    @Autowired
    private FabricaOdontologo fabricaOdontologo;
    @Autowired
    private ServicioModificarOdontologo servicioModificarOdontologo;

    @Override
    public ComandoRespuesta<ComandoModificarOdontologo> ejecutar(ComandoModificarOdontologo comandoModificarOdontologo) {
        SolicitudModificarOdontologo solicitudModificarOdontologo = servicioModificarOdontologo
                .ejecutarModificarOdontologo(fabricaOdontologo.solicitudModificarOdontologo(comandoModificarOdontologo));
        return new ComandoRespuesta<>(fabricaOdontologo.modificarOdontologo(solicitudModificarOdontologo));
    }
}
