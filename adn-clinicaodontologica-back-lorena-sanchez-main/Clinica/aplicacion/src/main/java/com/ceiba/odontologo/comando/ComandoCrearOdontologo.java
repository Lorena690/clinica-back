package com.ceiba.odontologo.comando;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCrearOdontologo {
    @JsonProperty
    @Schema(allowableValues = {"Lorena"})
    private String nombre;
    @JsonProperty
    @Schema(allowableValues = {"Sanchez"})
    private String apellido;
    @JsonProperty
    @Schema(allowableValues = {"1234"})
    private String matricula;

}
