package com.ceiba.odontologo.servicio;

import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudCrearOdontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudCrearOdontologoTestDataBuilder;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


class ServicioCrearOdontologoTest {

    RepositorioOdontologo repositorioOdontologo = Mockito.mock(RepositorioOdontologo.class);

    DaoOdontologo daoOdontologo = Mockito.mock(DaoOdontologo.class);

    @Test
    void deberiaCrearOdontologoYGuardar() {
        ServicioCrearOdontologo servicioCrearOdontologo = new ServicioCrearOdontologo(repositorioOdontologo, daoOdontologo);

        SolicitudCrearOdontologo odontologo = new SolicitudCrearOdontologoTestDataBuilder()
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .crear();

        Mockito.doReturn(1L).when(repositorioOdontologo).guardar(Mockito.any());
        var idOdontologoCreado = servicioCrearOdontologo.ejecutar(odontologo);

        ArgumentCaptor<Odontologo> captorOdontologo = ArgumentCaptor.forClass(Odontologo.class);
        Mockito.verify(repositorioOdontologo, Mockito.times(1)).guardar(captorOdontologo.capture());
        Assertions.assertEquals(1L, idOdontologoCreado);
        Assertions.assertEquals("Lorena", captorOdontologo.getValue().getNombre());
        Assertions.assertEquals("Sanchez", captorOdontologo.getValue().getApellido());
        Assertions.assertEquals("1234", captorOdontologo.getValue().getMatricula());

    }
}
