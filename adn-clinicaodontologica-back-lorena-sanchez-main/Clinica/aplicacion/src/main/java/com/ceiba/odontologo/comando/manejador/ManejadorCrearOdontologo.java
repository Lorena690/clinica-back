package com.ceiba.odontologo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.odontologo.comando.ComandoCrearOdontologo;
import com.ceiba.odontologo.comando.fabrica.FabricaOdontologo;
import com.ceiba.odontologo.servicio.ServicioCrearOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearOdontologo implements ManejadorComandoRespuesta<ComandoCrearOdontologo, ComandoRespuesta<Long>> {

    @Autowired
    private FabricaOdontologo fabricaOdontologo;
    @Autowired
    private ServicioCrearOdontologo servicioCrearOdontologo;

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoCrearOdontologo comandoCrearOdontologo) {
        return new ComandoRespuesta<>(servicioCrearOdontologo
                .ejecutar(fabricaOdontologo.crear(comandoCrearOdontologo)));
    }
}
