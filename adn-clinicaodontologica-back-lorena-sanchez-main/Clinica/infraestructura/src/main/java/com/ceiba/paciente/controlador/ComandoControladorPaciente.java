package com.ceiba.paciente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.paciente.comando.ComandoCrearPaciente;
import com.ceiba.paciente.comando.ComandoModificarPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorCrearPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorEliminarPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorModificarPaciente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/paciente")
@Tag(name = "Controlador comando paciente")
public class ComandoControladorPaciente {
    @Autowired
    private ManejadorCrearPaciente manejadorCrearPaciente;
    @Autowired
    private ManejadorModificarPaciente manejadorModificarPaciente;

    @Autowired
    private ManejadorEliminarPaciente manejadorEliminarPaciente;

    @PostMapping
    @Operation(summary = "Crear paciente", description = "Método utilizado para crear un nuevo paciente")
    public ResponseEntity<ComandoRespuesta<Long>> paciente(@RequestBody ComandoCrearPaciente comandoCrearPaciente) {
        return new ResponseEntity<>(manejadorCrearPaciente.ejecutar(comandoCrearPaciente), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Modificar paciente", description = "Método utilizado para modificar un paciente")
    public ResponseEntity<ComandoRespuesta<ComandoModificarPaciente>> actualizarPaciente(@RequestBody ComandoModificarPaciente comandoModificarPaciente) {
        return ResponseEntity.ok(manejadorModificarPaciente.ejecutar(comandoModificarPaciente));
    }

    @DeleteMapping("{id-paciente}")
    @Operation(summary = "Eliminar paciente", description = "Método utilizado para eliminar un paciente")
    public void eliminarPaciente(@PathVariable("id-paciente") Long idPaciente) {
        manejadorEliminarPaciente.ejecutar(idPaciente);
    }

}
