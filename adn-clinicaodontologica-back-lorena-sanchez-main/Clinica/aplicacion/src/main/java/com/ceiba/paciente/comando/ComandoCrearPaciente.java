package com.ceiba.paciente.comando;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ComandoCrearPaciente {
    @JsonProperty
    @Schema(allowableValues = {"Lorena"})
    private String nombre;
    @JsonProperty
    @Schema(allowableValues = {"Sanchez"})
    private String apellido;
    @JsonProperty
    @Schema(allowableValues = {"2"})
    private String estrato;
    @JsonProperty
    @Schema(allowableValues = {"111624"})
    private String documento;
    @JsonProperty
    @Schema(allowableValues = {"Calle 00 # 00 - 00"})
    private String domicilio;
}
