package com.ceiba.paciente.servicio;


import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.SolicitudCrearPaciente;
import com.ceiba.paciente.modelo.entidad.SolicitudCrearPacienteTestDataBuilder;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


class ServicioCrearPacienteTest {

    RepositorioPaciente repositorioPaciente = Mockito.mock(RepositorioPaciente.class);

    DaoPaciente daoPaciente = Mockito.mock(DaoPaciente.class);

    @Test
    void deberiaCrearPacienteYGuardar() {
        ServicioCrearPaciente servicioCrearPaciente = new ServicioCrearPaciente(repositorioPaciente, daoPaciente);

        SolicitudCrearPaciente paciente = new SolicitudCrearPacienteTestDataBuilder()
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .crear();

        Mockito.doReturn(1L).when(repositorioPaciente).guardar(Mockito.any());
        var idPacienteCreado = servicioCrearPaciente.ejecutar(paciente);

        ArgumentCaptor<Paciente> captorPaciente = ArgumentCaptor.forClass(Paciente.class);
        Mockito.verify(repositorioPaciente, Mockito.times(1)).guardar(captorPaciente.capture());
        Assertions.assertEquals(1L, idPacienteCreado);
        Assertions.assertEquals("Lorena", captorPaciente.getValue().getNombre());
        Assertions.assertEquals("Sanchez", captorPaciente.getValue().getApellido());
        Assertions.assertEquals("1", captorPaciente.getValue().getEstrato());
        Assertions.assertEquals("1234", captorPaciente.getValue().getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", captorPaciente.getValue().getDomicilio());
    }
}
