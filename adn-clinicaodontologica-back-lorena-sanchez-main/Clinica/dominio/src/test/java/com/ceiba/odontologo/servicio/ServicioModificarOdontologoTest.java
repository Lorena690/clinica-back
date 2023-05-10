package com.ceiba.odontologo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologoTestDataBuilder;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class ServicioModificarOdontologoTest {

    RepositorioOdontologo repositorioOdontologo = Mockito.mock(RepositorioOdontologo.class);

    DaoOdontologo daoOdontologo = Mockito.mock(DaoOdontologo.class);

    @Test
    void deberiaActualizarOdontologoYGuardar() {
        ServicioModificarOdontologo servicioModificarOdontologo = new ServicioModificarOdontologo(repositorioOdontologo, daoOdontologo);

        SolicitudModificarOdontologo odontologo = new SolicitudModificarOdontologoTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .crear();

        Mockito.doReturn(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear()).when(daoOdontologo).obtenerOdontologoPorId(Mockito.any());
        Mockito.doReturn(odontologo).when(repositorioOdontologo).actualizar(Mockito.any());
        var odontologoActualizado = servicioModificarOdontologo.ejecutarModificarOdontologo(odontologo);

        Assertions.assertEquals(1L, odontologoActualizado.getId());
        Assertions.assertEquals("Lorena", odontologoActualizado.getNombre());
        Assertions.assertEquals("Sanchez", odontologoActualizado.getApellido());
        Assertions.assertEquals("1234", odontologoActualizado.getMatricula());
    }

    @Test
    void deberiaNoActualizarOdontologoYlanzarError() {
        ServicioModificarOdontologo servicioModificarOdontologo = new ServicioModificarOdontologo(repositorioOdontologo, daoOdontologo);

        SolicitudModificarOdontologo odontologo = new SolicitudModificarOdontologoTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .crear();

        Mockito.doReturn(null).when(daoOdontologo).obtenerOdontologoPorId(Mockito.any());
        Mockito.doReturn(odontologo).when(repositorioOdontologo).actualizar(Mockito.any());

        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> servicioModificarOdontologo.ejecutarModificarOdontologo(odontologo));

    }
}
