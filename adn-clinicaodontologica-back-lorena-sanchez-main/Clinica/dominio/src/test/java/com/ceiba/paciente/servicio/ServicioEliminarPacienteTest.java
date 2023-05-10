package com.ceiba.paciente.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


class ServicioEliminarPacienteTest {

    RepositorioPaciente repositorioPaciente = Mockito.mock(RepositorioPaciente.class);

    DaoPaciente daoPaciente = Mockito.mock(DaoPaciente.class);

    @Test
    void deberiaEliminarPaciente() {
        ServicioEliminarPaciente servicioEliminarPaciente = new ServicioEliminarPaciente(repositorioPaciente, daoPaciente);

        PacienteDTO paciente = new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear();

        Mockito.doReturn(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear()).when(daoPaciente).obtenerPacientePorId(Mockito.any());
        Mockito.doNothing().when(repositorioPaciente).eliminarPaciente(Mockito.any());
        servicioEliminarPaciente.ejecutarEliminarPaciente(paciente.getId());

        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repositorioPaciente, Mockito.times(1)).eliminarPaciente(captorId.capture());
        Assertions.assertEquals(1L, captorId.getValue());
    }

    @Test
    void deberiaNoEliminarOdontologoYlanzarError() {
        ServicioEliminarPaciente servicioEliminarPaciente = new ServicioEliminarPaciente(repositorioPaciente, daoPaciente);


        Mockito.doReturn(null).when(daoPaciente).obtenerPacientePorId(Mockito.any());
        Mockito.doNothing().when(repositorioPaciente).eliminarPaciente(Mockito.any());
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> servicioEliminarPaciente.ejecutarEliminarPaciente(1L));
    }
}
