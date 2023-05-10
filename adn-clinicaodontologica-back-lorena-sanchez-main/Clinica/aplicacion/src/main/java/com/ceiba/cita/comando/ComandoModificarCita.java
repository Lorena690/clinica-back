package com.ceiba.cita.comando;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComandoModificarCita {
    @JsonProperty
    @Schema(allowableValues = {"1"})
    private Long id;
    @JsonProperty("id_odontologo")
    @Schema(allowableValues = {"1"})
    private Long idOdontologo;
    @JsonProperty
    private LocalDateTime fechaYHora;
    @JsonProperty
    @Schema(allowableValues = {"true"})
    private Boolean especialista;

    @JsonProperty
    @Schema(allowableValues = {"50000"})
    private Double valor;

}
