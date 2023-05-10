package com.ceiba.odontologo.comando;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComandoModificarOdontologo {
    @JsonProperty
    @Schema(allowableValues = {"1"})
    private Long id;
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
