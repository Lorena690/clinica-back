package com.ceiba.paciente.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.paciente.comando.ComandoModificarPaciente;
import com.ceiba.paciente.comando.fabrica.FabricaPaciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;
import com.ceiba.paciente.servicio.ServicioModificarPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorModificarPaciente implements ManejadorComandoRespuesta<ComandoModificarPaciente, ComandoRespuesta<ComandoModificarPaciente>> {

    @Autowired
    private FabricaPaciente fabricaPaciente;
    @Autowired
    private ServicioModificarPaciente servicioModificarPaciente;

    @Override
    public ComandoRespuesta<ComandoModificarPaciente> ejecutar(ComandoModificarPaciente comandoModificarPaciente) {
        SolicitudModificarPaciente solicitudModificarPaciente = servicioModificarPaciente
                .ejecutarModificarPaciente(fabricaPaciente.solicitudModificarPaciente(comandoModificarPaciente));
        return new ComandoRespuesta<>(fabricaPaciente.modificarPaciente(solicitudModificarPaciente));
    }
}
