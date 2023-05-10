package com.ceiba.paciente.controlador.comando;

import com.ceiba.ApplicationMock;
import com.ceiba.paciente.comando.ComandoCrearPaciente;
import com.ceiba.paciente.comando.ComandoModificarPaciente;
import com.ceiba.paciente.controlador.ComandoControladorPaciente;
import com.ceiba.paciente.controlador.ConsultaControladorPaciente;
import com.ceiba.paciente.controlador.RespuestaPaciente;
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
@WebMvcTest({ConsultaControladorPaciente.class, ComandoControladorPaciente.class})
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorPacienteTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void crearPacienteExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto().crear();

        mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

    }

    @Test
    void crearPacienteNoExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto()
                .conNombre(null)
                .crear();

        mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("El nombre es requerido para crear un paciente")))
                .andReturn();

    }

    @Test
    void editarPacienteExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto()
                .conNombre("MarySelva")
                .crear();

        var resultadoCrear = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaPaciente.class);

        ComandoModificarPaciente comandoModificarPaciente =
                new ComandoModificarPacienteTestDataBuilder()
                        .crearPorDefecto()
                        .conId(respuesta.getValor())
                        .crear();

        mocMvc.perform(put("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarPaciente)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor.id", is(1)))
                .andExpect(jsonPath("$.valor.nombre", is("Lorena")))
                .andReturn();

    }

    @Test
    void editarPacienteNoExitoso() throws Exception {

        ComandoModificarPaciente comandoModificarPaciente =
                new ComandoModificarPacienteTestDataBuilder()
                        .crearPorDefecto()
                        .crear();

        mocMvc.perform(put("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarPaciente)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorObligatorio")))
                .andExpect(jsonPath("$.mensaje", is("El paciente con id: " + comandoModificarPaciente.getId() + " no existe")))
                .andReturn();

    }

    @Test
    void eliminarPacienteExitoso() throws Exception {

        ComandoCrearPaciente comandoCrearPaciente = new ComandoPacienteTestDataBuilder().crearPorDefecto()
                .crear();

        var resultadoCrear = mocMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCrearPaciente)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.valor", is(1)))
                .andReturn();

        String jsonResult = resultadoCrear.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaPaciente.class);

        mocMvc.perform(delete("/paciente/" + respuesta.getValor())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void eliminarPacienteNoExitoso() throws Exception {

        mocMvc.perform(delete("/paciente/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorObligatorio")))
                .andExpect(jsonPath("$.mensaje", is("El paciente con id: 4 no existe")))
                .andReturn();

    }

}
