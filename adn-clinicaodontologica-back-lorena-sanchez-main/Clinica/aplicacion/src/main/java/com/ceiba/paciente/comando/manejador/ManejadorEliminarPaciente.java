package com.ceiba.paciente.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.paciente.servicio.ServicioEliminarPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarPaciente implements ManejadorComando<Long> {

    @Autowired
    private ServicioEliminarPaciente servicioEliminarPaciente;

    @Override
    public void ejecutar(Long idPaciente) {
        servicioEliminarPaciente.ejecutarEliminarPaciente(idPaciente);
    }
}
