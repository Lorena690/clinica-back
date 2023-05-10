package com.ceiba.odontologo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.odontologo.servicio.ServicioEliminarOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarOdontologo implements ManejadorComando<Long> {

    @Autowired
    private ServicioEliminarOdontologo servicioEliminarOdontologo;

    @Override
    public void ejecutar(Long idOdontologo) {
        servicioEliminarOdontologo.ejecutarEliminarOdontologo(idOdontologo);
    }
}
