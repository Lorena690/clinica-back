package com.ceiba.odontologo.controlador.comando;

import com.ceiba.ApplicationMock;
import com.ceiba.odontologo.comando.ComandoCrearOdontologo;
import com.ceiba.odontologo.comando.ComandoModificarOdontologo;
import com.ceiba.odontologo.controlador.ComandoControladorOdontologo;
import com.ceiba.odontologo.controlador.ConsultaControladorOdontologo;
import com.ceiba.odontologo.controlador.RespuestaOdontologo;
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
@WebMvcTest({ConsultaControladorOdontologo.class, ComandoControladorOdontologo.class})
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorOdontologoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void crearOdontologoExitoso() throws Exception {

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto().crear();

        mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

    }

    @Test
    void crearOdontologoNoExitoso() throws Exception {

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto()
                .conNombre(null)
                .crear();

        mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("El nombre es requerido para crear un odontologo")))
                .andReturn();

    }

    @Test
    void editarOdontologoExitoso() throws Exception {

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto()
                .conNombre("MarySelva")
                .crear();

        var resultadoCrear = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaOdontologo.class);

        ComandoModificarOdontologo comandoModificarOdontologo =
                new ComandoModificarOdontologoTestDataBuilder()
                        .crearPorDefecto()
                        .conId(respuesta.getValor())
                        .crear();

        mocMvc.perform(put("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarOdontologo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor.id", is(1)))
                .andExpect(jsonPath("$.valor.nombre", is("Lorena")))
                .andReturn();

    }

    @Test
    void editarOdontologoNoExitoso() throws Exception {

        ComandoModificarOdontologo comandoModificarOdontologo =
                new ComandoModificarOdontologoTestDataBuilder()
                        .crearPorDefecto()
                        .crear();

        mocMvc.perform(put("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarOdontologo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorObligatorio")))
                .andExpect(jsonPath("$.mensaje", is("El odontologo con id: " + comandoModificarOdontologo.getId() + " no existe")))
                .andReturn();

    }

    @Test
    void eliminarOdontologoExitoso() throws Exception {

        ComandoCrearOdontologo comandoCrearOdontologo = new ComandoOdontologoTestDataBuilder().crearPorDefecto()
                .crear();

        var resultadoCrear = mocMvc.perform(post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearOdontologo)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaOdontologo.class);

        mocMvc.perform(delete("/odontologo/" + respuesta.getValor())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void eliminarOdontologoNoExitoso() throws Exception {

        mocMvc.perform(delete("/odontologo/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorObligatorio")))
                .andExpect(jsonPath("$.mensaje", is("El odontologo con id: 4 no existe")))
                .andReturn();

    }

}
