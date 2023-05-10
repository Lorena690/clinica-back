package com.ceiba.odontologo.controlador;

import com.ceiba.odontologo.consulta.manejador.ManejadorConsultarOdontologo;
import com.ceiba.odontologo.consulta.manejador.ManejadorListarOdontologos;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/odontologo")
@Tag(name = "Controlador consulta odontologo")
public class ConsultaControladorOdontologo {
    @Autowired
    private ManejadorConsultarOdontologo manejadorConsultarOdontologo;

    @Autowired
    private ManejadorListarOdontologos manejadorListarOdontologos;

    @GetMapping("{id-odontologo}")
    @Operation(summary = "Obtener odontologo", description = "Método utilizado para obtener un odontologo por Id")
    public ResponseEntity<OdontologoDTO> consultarOdontologo(@PathVariable("id-odontologo") Long id) {
        return ResponseEntity.ok(manejadorConsultarOdontologo.ejecutar(id));
    }

    @GetMapping("listar")
    @Operation(summary = "Listar odontologos", description = "Método utilizado para listar los odontologos")
    public ResponseEntity<List<OdontologoDTO>> listarOdontologos() {
        return ResponseEntity.ok(manejadorListarOdontologos.ejecutar());
    }
}
