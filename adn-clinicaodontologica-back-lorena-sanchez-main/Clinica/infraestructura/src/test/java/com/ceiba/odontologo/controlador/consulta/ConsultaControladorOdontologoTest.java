package com.ceiba.odontologo.controlador.consulta;

import com.ceiba.ApplicationMock;
import com.ceiba.odontologo.comando.ComandoCrearOdontologo;
import com.ceiba.odontologo.controlador.ComandoControladorOdontologo;
import com.ceiba.odontologo.controlador.ConsultaControladorOdontologo;
import com.ceiba.odontologo.controlador.RespuestaOdontologo;
import com.ceiba.odontologo.controlador.comando.ComandoOdontologoTestDataBuilder;
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
@WebMvcTest({ConsultaControladorOdontologo.class, ComandoControladorOdontologo.class})
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorOdontologoTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarOdontologoPorId() throws Exception {

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrear = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaOdontologo.class);

        mocMvc.perform(get("/odontologo/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(respuesta.getValor().intValue())))
                .andExpect(jsonPath("$.nombre", is("Lorena")))
                .andExpect(jsonPath("$.apellido", is("Ocampo")));

    }

    @Test
    void consultarOdontologoPorIdNoExitosa() throws Exception {

        mocMvc.perform(get("/odontologo/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionSinDatos")))
                .andExpect(jsonPath("$.mensaje", is("El odontologo con id: 1 no existe")))
                .andReturn();
    }

    @Test
    void listarOdontologos() throws Exception {

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto().crear();

        var resultadoCrear = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        var resultadoCrear2 = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaOdontologo.class);

        String jsonResult2 = resultadoCrear2.getResponse().getContentAsString();
        var respuesta2 = objectMapper.readValue(jsonResult2, RespuestaOdontologo.class);

        mocMvc.perform(get("/odontologo/listar")
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
