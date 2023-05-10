package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.dto.CitaDTOTestDataBuilder;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


class ServicioCancelarCitaTest {

    RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);

    DaoCita daoCita = Mockito.mock(DaoCita.class);

    @Test
    void deberiaCancelarCita() {
        ServicioCancelarCita servicioCancelarCita = new ServicioCancelarCita(repositorioCita, daoCita);

        Mockito.doReturn(new CitaDTOTestDataBuilder().conCitaPorDefecto().crear()).when(daoCita).obtenerCitaPorId(Mockito.any());
        Mockito.doNothing().when(repositorioCita).cancelarCita(Mockito.any());
        servicioCancelarCita.ejecutarCancelarCita(1L);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(1)).cancelarCita(captorCita.capture());
        Assertions.assertEquals(5L, captorCita.getValue().getId());
        Assertions.assertEquals("Lore", captorCita.getValue().getPaciente().getNombre());
        Assertions.assertEquals("Lorena", captorCita.getValue().getOdontologo().getNombre());
        Assertions.assertEquals(false, captorCita.getValue().getEspecialista());
        Assertions.assertEquals(36000.0, captorCita.getValue().getValor());
        Assertions.assertEquals(EstadoCita.CANCELADA, captorCita.getValue().getEstado());
    }

    @Test
    void deberiaNocancelarLaCitaYlanzarError() {
        ServicioCancelarCita servicioCancelarCita = new ServicioCancelarCita(repositorioCita, daoCita);


        Mockito.doReturn(null).when(daoCita).obtenerCitaPorId(Mockito.any());
        Mockito.doNothing().when(repositorioCita).cancelarCita(Mockito.any());
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> servicioCancelarCita.ejecutarCancelarCita(1L));
    }
}
