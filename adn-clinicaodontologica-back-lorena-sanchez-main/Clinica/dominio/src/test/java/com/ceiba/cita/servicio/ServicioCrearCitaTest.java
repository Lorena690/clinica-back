package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.SolicitarCrearCitaTestDataBuilder;
import com.ceiba.cita.modelo.entidad.SolicitudCrearCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static com.ceiba.cita.servicio.CitaTest.calcularFechaSinFestivo;


class ServicioCrearCitaTest {

    RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);

    DaoCita daoCita = Mockito.mock(DaoCita.class);

    @Test
    void deberiaCrearCitaEstrato1YGuardar() {
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        LocalDateTime fecha = calcularFechaSinFestivo();

        SolicitudCrearCita cita = new SolicitarCrearCitaTestDataBuilder()
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .crear();

        Mockito.doReturn(1L).doReturn(2L).when(repositorioCita).guardar(Mockito.any());
        var idCitaCreado = servicioCrearCita.ejecutar(cita);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(2)).guardar(captorCita.capture());
        Assertions.assertEquals(2L, idCitaCreado);
        Assertions.assertEquals("Lore", captorCita.getValue().getPaciente().getNombre());
        Assertions.assertEquals("Lorena", captorCita.getValue().getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), captorCita.getValue().getFechaYHora().toString());
        Assertions.assertEquals(true, captorCita.getValue().getEspecialista());
        Assertions.assertEquals(60000., captorCita.getValue().getValor());
        Assertions.assertEquals(EstadoCita.CREADA, captorCita.getValue().getEstado());
    }

    @Test
    void deberiaCrearCitaEstrato2YGuardar() {
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        LocalDateTime fecha = calcularFechaSinFestivo();

        SolicitudCrearCita cita = new SolicitarCrearCitaTestDataBuilder()
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().conEstrato("2").crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .crear();

        Mockito.doReturn(1L).doReturn(2L).when(repositorioCita).guardar(Mockito.any());
        var idCitaCreado = servicioCrearCita.ejecutar(cita);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(2)).guardar(captorCita.capture());
        Assertions.assertEquals(2L, idCitaCreado);
        Assertions.assertEquals("Lore", captorCita.getValue().getPaciente().getNombre());
        Assertions.assertEquals("Lorena", captorCita.getValue().getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), captorCita.getValue().getFechaYHora().toString());
        Assertions.assertEquals(true, captorCita.getValue().getEspecialista());
        Assertions.assertEquals(70000., captorCita.getValue().getValor());
        Assertions.assertEquals(EstadoCita.CREADA, captorCita.getValue().getEstado());
    }

    @Test
    void deberiaCrearCitaEstrato3YGuardar() {
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        LocalDateTime fecha = calcularFechaSinFestivo();

        SolicitudCrearCita cita = new SolicitarCrearCitaTestDataBuilder()
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().conEstrato("3").crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .crear();

        Mockito.doReturn(1L).doReturn(2L).when(repositorioCita).guardar(Mockito.any());
        var idCitaCreado = servicioCrearCita.ejecutar(cita);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(2)).guardar(captorCita.capture());
        Assertions.assertEquals(2L, idCitaCreado);
        Assertions.assertEquals("Lore", captorCita.getValue().getPaciente().getNombre());
        Assertions.assertEquals("Lorena", captorCita.getValue().getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), captorCita.getValue().getFechaYHora().toString());
        Assertions.assertEquals(true, captorCita.getValue().getEspecialista());
        Assertions.assertEquals(80000., captorCita.getValue().getValor());
        Assertions.assertEquals(EstadoCita.CREADA, captorCita.getValue().getEstado());
    }

    @Test
    void deberiaCrearCitaEstrato4YGuardar() {
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        LocalDateTime fecha = calcularFechaSinFestivo();

        SolicitudCrearCita cita = new SolicitarCrearCitaTestDataBuilder()
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().conEstrato("4").crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .crear();

        Mockito.doReturn(1L).doReturn(2L).when(repositorioCita).guardar(Mockito.any());
        var idCitaCreado = servicioCrearCita.ejecutar(cita);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(2)).guardar(captorCita.capture());
        Assertions.assertEquals(2L, idCitaCreado);
        Assertions.assertEquals("Lore", captorCita.getValue().getPaciente().getNombre());
        Assertions.assertEquals("Lorena", captorCita.getValue().getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), captorCita.getValue().getFechaYHora().toString());
        Assertions.assertEquals(true, captorCita.getValue().getEspecialista());
        Assertions.assertEquals(100000., captorCita.getValue().getValor());
        Assertions.assertEquals(EstadoCita.CREADA, captorCita.getValue().getEstado());
    }
}
