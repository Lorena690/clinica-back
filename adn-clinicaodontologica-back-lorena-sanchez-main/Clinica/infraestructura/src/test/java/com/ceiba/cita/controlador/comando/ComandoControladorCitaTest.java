package com.ceiba.cita.controlador.comando;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.comando.ComandoCrearCita;
import com.ceiba.cita.comando.ComandoModificarCita;
import com.ceiba.cita.controlador.ComandoControladorCita;
import com.ceiba.cita.controlador.ConsultaControladorCita;
import com.ceiba.cita.controlador.RespuestaCita;
import com.ceiba.odontologo.comando.ComandoCrearOdontologo;
import com.ceiba.odontologo.controlador.RespuestaOdontologo;
import com.ceiba.odontologo.controlador.comando.ComandoOdontologoTestDataBuilder;
import com.ceiba.paciente.comando.ComandoCrearPaciente;
import com.ceiba.paciente.controlador.RespuestaPaciente;
import com.ceiba.paciente.controlador.comando.ComandoPacienteTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ConsultaControladorCita.class, ComandoControladorCita.class})
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void crearCitaExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrearPaciente = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultPaciente = resultadoCrearPaciente.getResponse().getContentAsString();
        var respuestaPaciente = objectMapper.readValue(jsonResultPaciente, RespuestaPaciente.class);

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrearOdontologo = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultOdontologo = resultadoCrearOdontologo.getResponse().getContentAsString();
        var respuestaOdontologo = objectMapper.readValue(jsonResultOdontologo, RespuestaOdontologo.class);

        ComandoCrearCita comandoCrearCita = new ComandoCitaTestDataBuilder()
                .crearPorDefecto()
                .conIdPaciente(respuestaPaciente.getValor())
                .conIdOdontologo(respuestaOdontologo.getValor())
                .crear();

        mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearCita)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

    }

    @Test
    void crearCitaNoExitoso() throws Exception {

        ComandoCrearCita comandoCrearCita = new ComandoCitaTestDataBuilder().crearPorDefecto()
                .crear();

        mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearCita)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("El paciente no existe")))
                .andReturn();

    }

    @Test
    void editarCitaExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrearPaciente = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultPaciente = resultadoCrearPaciente.getResponse().getContentAsString();
        var respuestaPaciente = objectMapper.readValue(jsonResultPaciente, RespuestaPaciente.class);

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrearOdontologo = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultOdontologo = resultadoCrearOdontologo.getResponse().getContentAsString();
        var respuestaOdontologo = objectMapper.readValue(jsonResultOdontologo, RespuestaOdontologo.class);

        ComandoCrearCita comandoCrearCita = new ComandoCitaTestDataBuilder()
                .crearPorDefecto()
                .conIdPaciente(respuestaPaciente.getValor())
                .conIdOdontologo(respuestaOdontologo.getValor())
                .crear();

        var resultadoCrear = mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearCita)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaCita.class);

        ComandoModificarCita comandoModificarCita =
                new ComandoModificarCitaTestDataBuilder()
                        .crearPorDefecto()
                        .conId(respuesta.getValor())
                        .conValor(0.0)
                        .crear();

        mocMvc.perform(put("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarCita)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor.id", is(1)))
                .andExpect(jsonPath("$.valor.valor", is(0.0)))
                .andReturn();

    }

    @Test
    void editarCitaNoExitoso() throws Exception {

        ComandoModificarCita comandoModificarCita =
                new ComandoModificarCitaTestDataBuilder()
                        .crearPorDefecto()
                        .crear();

        mocMvc.perform(put("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarCita)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorObligatorio")))
                .andExpect(jsonPath("$.mensaje", is("la cita con id 1 no existe")))
                .andReturn();

    }

    @Test
    void eliminarCitaExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrearPaciente = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultPaciente = resultadoCrearPaciente.getResponse().getContentAsString();
        var respuestaPaciente = objectMapper.readValue(jsonResultPaciente, RespuestaPaciente.class);

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrearOdontologo = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultOdontologo = resultadoCrearOdontologo.getResponse().getContentAsString();
        var respuestaOdontologo = objectMapper.readValue(jsonResultOdontologo, RespuestaOdontologo.class);

        ComandoCrearCita comandoCrearCita = new ComandoCitaTestDataBuilder()
                .crearPorDefecto()
                .conIdPaciente(respuestaPaciente.getValor())
                .conIdOdontologo(respuestaOdontologo.getValor())
                .crear();

        var resultadoCrear = mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearCita)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaCita.class);

        mocMvc.perform(delete("/cita/" + respuesta.getValor())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void eliminarCitaNoExitoso() throws Exception {

        mocMvc.perform(delete("/cita/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorObligatorio")))
                .andExpect(jsonPath("$.mensaje", is("La cita con id 4 no existe")))
                .andReturn();

    }

}
