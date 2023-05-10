package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.manejador.ManejadorConsultarCita;
import com.ceiba.cita.consulta.manejador.ManejadorListarCitas;
import com.ceiba.cita.modelo.dto.CitaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador consulta cita")
public class ConsultaControladorCita {
    @Autowired
    private ManejadorConsultarCita manejadorConsultarCita;

    @Autowired
    private ManejadorListarCitas manejadorListarCitas;

    @GetMapping("{id-cita}")
    @Operation(summary = "Obtener cita", description = "Método utilizado para obtener un cita por Id")
    public ResponseEntity<CitaDTO> consultarCita(@PathVariable("id-cita") Long id) {
        return ResponseEntity.ok(manejadorConsultarCita.ejecutar(id));
    }

    @GetMapping("listar")
    @Operation(summary = "Listar citas", description = "Método utilizado para listar los citas")
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        return ResponseEntity.ok(manejadorListarCitas.ejecutar());
    }
}
