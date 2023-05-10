package com.ceiba.odontologo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.odontologo.comando.ComandoCrearOdontologo;
import com.ceiba.odontologo.comando.ComandoModificarOdontologo;
import com.ceiba.odontologo.comando.manejador.ManejadorCrearOdontologo;
import com.ceiba.odontologo.comando.manejador.ManejadorEliminarOdontologo;
import com.ceiba.odontologo.comando.manejador.ManejadorModificarOdontologo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/odontologo")
@Tag(name = "Controlador comando odontologo")
public class ComandoControladorOdontologo {
    @Autowired
    private ManejadorCrearOdontologo manejadorCrearOdontologo;
    @Autowired
    private ManejadorModificarOdontologo manejadorModificarOdontologo;

    @Autowired
    private ManejadorEliminarOdontologo manejadorEliminarOdontologo;

    @PostMapping
    @Operation(summary = "Crear odontologo", description = "Método utilizado para crear un nuevo odontologo")
    public ResponseEntity<ComandoRespuesta<Long>> odontologo(@RequestBody ComandoCrearOdontologo comandoCrearOdontologo) {
        return new ResponseEntity<>(manejadorCrearOdontologo.ejecutar(comandoCrearOdontologo), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Modificar odontologo", description = "Método utilizado para modificar un odontologo")
    public ResponseEntity<ComandoRespuesta<ComandoModificarOdontologo>> actualizarOdontologo(@RequestBody ComandoModificarOdontologo comandoModificarOdontologo) {
        return ResponseEntity.ok(manejadorModificarOdontologo.ejecutar(comandoModificarOdontologo));
    }

    @DeleteMapping("{id-odontologo}")
    @Operation(summary = "Eliminar odontologo", description = "Método utilizado para eliminar un odontologo")
    public void eliminarOdontologo(@PathVariable("id-odontologo") Long idOdontologo) {
        manejadorEliminarOdontologo.ejecutar(idOdontologo);
    }

}
