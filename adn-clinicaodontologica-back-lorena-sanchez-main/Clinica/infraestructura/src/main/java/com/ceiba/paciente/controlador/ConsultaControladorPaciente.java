package com.ceiba.paciente.controlador;

import com.ceiba.paciente.consulta.manejador.ManejadorConsultarPaciente;
import com.ceiba.paciente.consulta.manejador.ManejadorListarPacientes;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/paciente")
@Tag(name = "Controlador consulta paciente")
public class ConsultaControladorPaciente {
    @Autowired
    private ManejadorConsultarPaciente manejadorConsultarPaciente;

    @Autowired
    private ManejadorListarPacientes manejadorListarPacientes;

    @GetMapping("{id-paciente}")
    @Operation(summary = "Obtener paciente", description = "Método utilizado para obtener un paciente por Id")
    public ResponseEntity<PacienteDTO> consultarPaciente(@PathVariable("id-paciente") Long id) {
        return ResponseEntity.ok(manejadorConsultarPaciente.ejecutar(id));
    }

    @GetMapping("listar")
    @Operation(summary = "Listar pacientes", description = "Método utilizado para listar los pacientes")
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        return ResponseEntity.ok(manejadorListarPacientes.ejecutar());
    }
}
