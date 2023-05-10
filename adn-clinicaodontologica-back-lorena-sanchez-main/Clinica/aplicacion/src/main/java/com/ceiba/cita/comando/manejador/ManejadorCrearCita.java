package com.ceiba.cita.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCrearCita;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearCita implements ManejadorComandoRespuesta<ComandoCrearCita, ComandoRespuesta<Long>> {

    @Autowired
    private FabricaCita fabricaCita;
    @Autowired
    private ServicioCrearCita servicioCrearCita;


    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoCrearCita comandoCrearCita) {
        return new ComandoRespuesta<>(servicioCrearCita
                .ejecutar(fabricaCita.crear(comandoCrearCita)));
    }
}
