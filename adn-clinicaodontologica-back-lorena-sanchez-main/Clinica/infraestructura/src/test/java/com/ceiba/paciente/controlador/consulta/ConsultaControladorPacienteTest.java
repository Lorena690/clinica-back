package com.ceiba.paciente.controlador.consulta;

import com.ceiba.ApplicationMock;
import com.ceiba.paciente.comando.ComandoCrearPaciente;
import com.ceiba.paciente.controlador.ComandoControladorPaciente;
import com.ceiba.paciente.controlador.ConsultaControladorPaciente;
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
@WebMvcTest({ConsultaControladorPaciente.class, ComandoControladorPaciente.class})
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorPacienteTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarPacientePorId() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrear = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaPaciente.class);

        mocMvc.perform(get("/paciente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(respuesta.getValor().intValue())))
                .andExpect(jsonPath("$.nombre", is("Lorena")))
                .andExpect(jsonPath("$.apellido", is("Ocampo")));

    }

    @Test
    void consultarPacientePorIdNoExitosa() throws Exception {

        mocMvc.perform(get("/paciente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionSinDatos")))
                .andExpect(jsonPath("$.mensaje", is("El paciente con id: 1 no existe")))
                .andReturn();
    }

    @Test
    void listarPacientes() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrear = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        var resultadoCrear2 = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaPaciente.class);

        String jsonResult2 = resultadoCrear2.getResponse().getContentAsString();
        var respuesta2 = objectMapper.readValue(jsonResult2, RespuestaPaciente.class);

        mocMvc.perform(get("/paciente/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(respuesta.getValor().intValue())))
                .andExpect(jsonPath("$[0].nombre", is("Lorena")))
                .andExpect(jsonPath("$[0].apellido", is("Ocampo")))
                .andExpect(jsonPath("$[1].id", is(respuesta2.getValor().intValue())))
                .andExpect(jsonPath("$[1].nombre", is("Lorena")))
                .andExpect(jsonPath("$[1].apellido", is("Ocampo")));

    }

}
