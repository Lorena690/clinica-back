package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.dto.CitaDTO;
import com.ceiba.cita.modelo.dto.CitaDTOTestDataBuilder;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.SolicitudModificarCita;
import com.ceiba.cita.modelo.entidad.SolicitudModificarCitaTestDataBuilder;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static com.ceiba.cita.servicio.CitaTest.calcularFechaSinFestivo;


class ServicioModificarCitaTest {

    RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);

    DaoCita daoCita = Mockito.mock(DaoCita.class);

    @Test
    void deberiaActualizarCitaYGuardar() {
        ServicioModificarCita servicioModificarCita = new ServicioModificarCita(repositorioCita, daoCita);
        LocalDateTime fecha = calcularFechaSinFestivo();

        SolicitudModificarCita cita = new SolicitudModificarCitaTestDataBuilder()
                .conId(1L)
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .conEstado(EstadoCita.CREADA)
                .crear();

        CitaDTO citaDto = new CitaDTOTestDataBuilder()
                .conId(1L)
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .conEstado(EstadoCita.MODIFICADA)
                .crear();

        Mockito.doReturn(new CitaDTOTestDataBuilder().conCitaPorDefecto().crear()).when(daoCita).obtenerCitaPorId(Mockito.any());
        Mockito.doReturn(citaDto).when(repositorioCita).actualizar(Mockito.any());
        var citaModificada = servicioModificarCita.ejecutarModificarCita(cita);

        Assertions.assertEquals(1L, citaModificada.getId());
        Assertions.assertEquals("Lore", citaModificada.getPaciente().getNombre());
        Assertions.assertEquals("Lorena", citaModificada.getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), citaModificada.getFechaYHora().toString());
        Assertions.assertEquals(true, citaModificada.getEspecialista());
        Assertions.assertEquals(100000., citaModificada.getValor());
        Assertions.assertEquals(EstadoCita.MODIFICADA, citaModificada.getEstado());
    }


    @Test
    void actualizarCitaDeberiaFallar() {
        ServicioModificarCita servicioModificarCita = new ServicioModificarCita(repositorioCita, daoCita);
        LocalDateTime fecha = calcularFechaSinFestivo();

        SolicitudModificarCita cita = new SolicitudModificarCitaTestDataBuilder()
                .conId(1L)
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .conEstado(EstadoCita.CREADA)
                .crear();

        Mockito.doReturn(null).when(daoCita).obtenerCitaPorId(Mockito.any());
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> servicioModificarCita.ejecutarModificarCita(cita));

    }
}
