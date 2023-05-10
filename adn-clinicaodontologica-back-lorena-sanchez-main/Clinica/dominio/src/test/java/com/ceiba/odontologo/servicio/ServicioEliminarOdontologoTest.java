package com.ceiba.odontologo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.odontologo.puerto.dao.DaoOdontologo;
import com.ceiba.odontologo.puerto.repositorio.RepositorioOdontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


class ServicioEliminarOdontologoTest {

    RepositorioOdontologo repositorioOdontologo = Mockito.mock(RepositorioOdontologo.class);

    DaoOdontologo daoOdontologo = Mockito.mock(DaoOdontologo.class);

    @Test
    void deberiaEliminarOdontologo() {
        ServicioEliminarOdontologo servicioEliminarOdontologo = new ServicioEliminarOdontologo(repositorioOdontologo, daoOdontologo);

        OdontologoDTO odontologo = new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear();

        Mockito.doReturn(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear()).when(daoOdontologo).obtenerOdontologoPorId(Mockito.any());
        Mockito.doNothing().when(repositorioOdontologo).eliminarOdontologo(Mockito.any());
        servicioEliminarOdontologo.ejecutarEliminarOdontologo(odontologo.getId());

        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repositorioOdontologo, Mockito.times(1)).eliminarOdontologo(captorId.capture());
        Assertions.assertEquals(1L, captorId.getValue());
    }

    @Test
    void deberiaNoEliminarOdontologoYlanzarError() {
        ServicioEliminarOdontologo servicioEliminarOdontologo = new ServicioEliminarOdontologo(repositorioOdontologo, daoOdontologo);


        Mockito.doReturn(null).when(daoOdontologo).obtenerOdontologoPorId(Mockito.any());
        Mockito.doNothing().when(repositorioOdontologo).eliminarOdontologo(Mockito.any());
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> servicioEliminarOdontologo.ejecutarEliminarOdontologo(1L));
    }
}
