package com.ceiba.paciente.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.paciente.comando.ComandoCrearPaciente;
import com.ceiba.paciente.comando.fabrica.FabricaPaciente;
import com.ceiba.paciente.servicio.ServicioCrearPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearPaciente implements ManejadorComandoRespuesta<ComandoCrearPaciente, ComandoRespuesta<Long>> {

    @Autowired
    private FabricaPaciente fabricaPaciente;
    @Autowired
    private ServicioCrearPaciente servicioCrearPaciente;

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoCrearPaciente comandoCrearPaciente) {
        return new ComandoRespuesta<>(servicioCrearPaciente
                .ejecutar(fabricaPaciente.crear(comandoCrearPaciente)));
    }
}
