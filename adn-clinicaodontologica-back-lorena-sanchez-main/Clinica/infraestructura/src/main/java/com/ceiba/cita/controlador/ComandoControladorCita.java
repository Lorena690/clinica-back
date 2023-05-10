package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCrearCita;
import com.ceiba.cita.comando.ComandoModificarCita;
import com.ceiba.cita.comando.manejador.ManejadorCancelarCita;
import com.ceiba.cita.comando.manejador.ManejadorCrearCita;
import com.ceiba.cita.comando.manejador.ManejadorModificarCita;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador comando cita")
public class ComandoControladorCita {
    @Autowired
    private ManejadorCrearCita manejadorCrearCita;
    @Autowired
    private ManejadorModificarCita manejadorModificarCita;

    @Autowired
    private ManejadorCancelarCita manejadorCancelarCita;

    @PostMapping
    @Operation(summary = "Crear cita", description = "Método utilizado para crear un nuevo cita")
    public ResponseEntity<ComandoRespuesta<Long>> cita(@RequestBody ComandoCrearCita comandoCrearCita) {
        return new ResponseEntity<>(manejadorCrearCita.ejecutar(comandoCrearCita), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Modificar cita", description = "Método utilizado para modificar un cita")
    public ResponseEntity<ComandoRespuesta<ComandoModificarCita>> actualizarCita(@RequestBody ComandoModificarCita comandoModificarCita) {
        return ResponseEntity.ok(manejadorModificarCita.ejecutar(comandoModificarCita));
    }

    @DeleteMapping("{id-cita}")
    @Operation(summary = "Cancelar Cita", description = "Método utilizado para cancelar un cita")
    public void cancelarCita(@PathVariable("id-cita") Long idCita) {
        manejadorCancelarCita.ejecutar(idCita);
    }

}
