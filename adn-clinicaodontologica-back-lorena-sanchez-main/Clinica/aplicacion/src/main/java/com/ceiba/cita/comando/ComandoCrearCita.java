package com.ceiba.cita.comando;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCrearCita {
    @JsonProperty("id_paciente")
    @Schema(allowableValues = {"1"})
    private Long idPaciente;
    @JsonProperty("id_odontologo")
    @Schema(allowableValues = {"1"})
    private Long idOdontologo;
    @JsonProperty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaYHora;
    @JsonProperty
    @Schema(allowableValues = {"true"})
    private Boolean especialista;
}
