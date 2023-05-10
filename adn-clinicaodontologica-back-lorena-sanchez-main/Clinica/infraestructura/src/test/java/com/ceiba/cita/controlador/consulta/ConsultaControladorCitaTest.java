package com.ceiba.cita.controlador.consulta;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.comando.ComandoCrearCita;
import com.ceiba.cita.controlador.ComandoControladorCita;
import com.ceiba.cita.controlador.ConsultaControladorCita;
import com.ceiba.cita.controlador.RespuestaCita;
import com.ceiba.cita.controlador.comando.ComandoCitaTestDataBuilder;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ConsultaControladorCita.class, ComandoControladorCita.class})
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorCitaTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarCitaPorId() throws Exception {

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
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaCita.class);

        mocMvc.perform(get("/cita/" + respuesta.getValor())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(respuesta.getValor().intValue())))
                .andExpect(jsonPath("$.paciente.id", is(respuestaPaciente.getValor().intValue())))
                .andExpect(jsonPath("$.odontologo.id", is(respuestaOdontologo.getValor().intValue())))
                .andExpect(jsonPath("$.especialista", is(false)))
                .andExpect(jsonPath("$.valor", is(60000.0)))
                .andExpect(jsonPath("$.estado", is("CREADA")));

    }

    @Test
    void consultarCitaPorIdNoExitosa() throws Exception {

        mocMvc.perform(get("/cita/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionSinDatos")))
                .andExpect(jsonPath("$.mensaje", is("La cita con id: 1 no existe")))
                .andReturn();
    }

    @Test
    void listarCitas() throws Exception {

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
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaCita.class);

        mocMvc.perform(get("/cita/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

}
