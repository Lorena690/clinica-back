package com.ceiba.cita.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoModificarCita;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.servicio.ServicioModificarCita;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorModificarCita implements ManejadorComandoRespuesta<ComandoModificarCita, ComandoRespuesta<ComandoModificarCita>> {

    @Autowired
    private FabricaCita fabricaCita;
    @Autowired
    private ServicioModificarCita servicioModificarCita;

    @Override
    public ComandoRespuesta<ComandoModificarCita> ejecutar(ComandoModificarCita comandoModificarCita) {
        CitaDTO citaDTO = servicioModificarCita
                .ejecutarModificarCita(fabricaCita.solicitudModificarCita(comandoModificarCita));
        return new ComandoRespuesta<>(fabricaCita.modificarCita(citaDTO));
    }
}
