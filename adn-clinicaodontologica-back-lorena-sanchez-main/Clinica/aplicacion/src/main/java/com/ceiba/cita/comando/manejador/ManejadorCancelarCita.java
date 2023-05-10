package com.ceiba.cita.comando.manejador;

import com.ceiba.cita.servicio.ServicioCancelarCita;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCancelarCita implements ManejadorComando<Long> {

    @Autowired
    private ServicioCancelarCita servicioCancelarCita;

    @Override
    public void ejecutar(Long idCita) {
        servicioCancelarCita.ejecutarCancelarCita(idCita);
    }
}
