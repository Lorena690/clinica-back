package com.ceiba.paciente.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPacienteTestDataBuilder;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class ServicioModificarPacienteTest {

    RepositorioPaciente repositorioPaciente = Mockito.mock(RepositorioPaciente.class);

    DaoPaciente daoPaciente = Mockito.mock(DaoPaciente.class);

    @Test
    void deberiaActualizarPacienteYGuardar() {
        ServicioModificarPaciente servicioModificarPaciente = new ServicioModificarPaciente(repositorioPaciente, daoPaciente);

        SolicitudModificarPaciente paciente = new SolicitudModificarPacienteTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .crear();

        Mockito.doReturn(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear()).when(daoPaciente).obtenerPacientePorId(Mockito.any());
        Mockito.doReturn(paciente).when(repositorioPaciente).actualizar(Mockito.any());
        var pacienteActualizado = servicioModificarPaciente.ejecutarModificarPaciente(paciente);

        Assertions.assertEquals(1L, pacienteActualizado.getId());
        Assertions.assertEquals("Lorena", pacienteActualizado.getNombre());
        Assertions.assertEquals("Sanchez", pacienteActualizado.getApellido());
        Assertions.assertEquals("1", pacienteActualizado.getEstrato());
        Assertions.assertEquals("1234", pacienteActualizado.getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", pacienteActualizado.getDomicilio());
    }


    @Test
    void deberiaNoActualizarPacienteYlanzarError() {
        ServicioModificarPaciente servicioModificarPaciente = new ServicioModificarPaciente(repositorioPaciente, daoPaciente);

        SolicitudModificarPaciente paciente = new SolicitudModificarPacienteTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .crear();

        Mockito.doReturn(null).when(daoPaciente).obtenerPacientePorId(Mockito.any());
        Mockito.doReturn(paciente).when(repositorioPaciente).actualizar(Mockito.any());

        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> servicioModificarPaciente.ejecutarModificarPaciente(paciente));

    }
}
