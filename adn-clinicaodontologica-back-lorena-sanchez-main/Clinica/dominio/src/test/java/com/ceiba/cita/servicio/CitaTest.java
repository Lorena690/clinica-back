package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.CitaTestDataBuilder;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

class CitaTest {

    @Test
    void deberiaCrearCitaExitosa() {

        LocalDateTime fecha = calcularFechaSinFestivo();

        Cita cita = new CitaTestDataBuilder().conCitaPorDefecto()
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .crear();

        Assertions.assertEquals("Lore", cita.getPaciente().getNombre());
        Assertions.assertEquals("Lorena", cita.getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), cita.getFechaYHora().toString());
        Assertions.assertEquals(true, cita.getEspecialista());
        Assertions.assertEquals(100000., cita.getValor());
        Assertions.assertEquals(EstadoCita.CREADA, cita.getEstado());

    }

    @Test
    void crearCitaSinPacienteDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El paciente no existe");
    }

    @Test
    void crearCitaSinOdontologoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El odontologo no existe");
    }

    @Test
    void crearCitaSinFechaYHoraDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "La fecha y hora son requeridos para crear una cita");
    }

    @Test
    void crearCitaSinEspecialistaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conValor(100000.)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El especialista es requerido para crear una cita");
    }

    @Test
    void crearCitaFinDeSemanaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaConFestivo())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .crear(),
                ExcepcionValorInvalido.class,
                "Los fines de semana no se asignan citas");
    }

    @Test
    void deberiaModificarCitaExitoso() {

        LocalDateTime fecha = calcularFechaSinFestivo();

        Cita cita = new CitaTestDataBuilder()
                .conId(5L)
                .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .modificar();

        Assertions.assertEquals(5L, cita.getId());
        Assertions.assertEquals("Lore", cita.getPaciente().getNombre());
        Assertions.assertEquals("Lorena", cita.getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), cita.getFechaYHora().toString());
        Assertions.assertEquals(true, cita.getEspecialista());
        Assertions.assertEquals(100000., cita.getValor());
        Assertions.assertEquals(EstadoCita.MODIFICADA, cita.getEstado());
    }

    @Test
    void modificarCitaSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "El id es requerido para modificar una cita");
    }

    @Test
    void modificarCitaConOdontologoNoExisteDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conId(5L)
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "El odontologo no existe");
    }

    @Test
    void modificarCitaSinFechaYHoraDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conId(5L)
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conEspecialista(true)
                        .conValor(100000.)
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "La fecha y hora son requeridos para modificar una cita");
    }

    @Test
    void modificarCitaSinEspecialistaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conId(5L)
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conValor(100000.)
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "El especialista es requerido para modificar una cita");
    }

    @Test
    void modificarCitaSinValorDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conId(5L)
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaSinFestivo())
                        .conEspecialista(true)
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "El valor es requerido para modificar una cita");
    }

    @Test
    void modificarCitaFinDeSemanaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new CitaTestDataBuilder()
                        .conId(5L)
                        .conPaciente(new PacienteDTOTestDataBuilder().conPacientePorDefecto().crear())
                        .conOdontologo(new OdontologoDTOTestDataBuilder().conOdontologoPorDefecto().crear())
                        .conFechaYHora(calcularFechaConFestivo())
                        .conValor(100000.)
                        .conEspecialista(true)
                        .modificar(),
                ExcepcionValorInvalido.class,
                "Los fines de semana no se asignan citas");
    }

    @Test
    void deberiaReconstruirCitaConFechaExitosa() {

        LocalDateTime fecha = calcularFechaSinFestivo();

        Cita cita = new CitaTestDataBuilder().conCitaPorDefecto()
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .crearConFecha();

        Assertions.assertEquals("Lore", cita.getPaciente().getNombre());
        Assertions.assertEquals("Lorena", cita.getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), cita.getFechaYHora().toString());
        Assertions.assertEquals(true, cita.getEspecialista());
        Assertions.assertEquals(100000., cita.getValor());
        Assertions.assertEquals(EstadoCita.CREADA, cita.getEstado());

    }

    @Test
    void deberiaReconstruirCitaExitosa() {

        LocalDateTime fecha = calcularFechaSinFestivo();

        Cita cita = new CitaTestDataBuilder().conCitaPorDefecto()
                .conFechaYHora(fecha)
                .conEspecialista(true)
                .conValor(100000.)
                .reconstruir();

        Assertions.assertEquals("Lore", cita.getPaciente().getNombre());
        Assertions.assertEquals("Lorena", cita.getOdontologo().getNombre());
        Assertions.assertEquals(fecha.toString(), cita.getFechaYHora().toString());
        Assertions.assertEquals(true, cita.getEspecialista());
        Assertions.assertEquals(100000., cita.getValor());
        Assertions.assertEquals(EstadoCita.CREADA, cita.getEstado());

    }

    @Test
    void reconstruirCitaSinIdDeberiaLanzarError() {
        LocalDateTime fecha = calcularFechaSinFestivo();

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conId(null)
                        .conFechaYHora(fecha)
                        .conEspecialista(true)
                        .conValor(100000.)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El id es requerido para crear una cita");
    }

    @Test
    void reconstruirCitaSinPacienteDeberiaLanzarError() {
        LocalDateTime fecha = calcularFechaSinFestivo();

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conPaciente(null)
                        .conFechaYHora(fecha)
                        .conEspecialista(true)
                        .conValor(100000.)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El paciente no existe");
    }

    @Test
    void reconstruirCitaSinOdontologoDeberiaLanzarError() {
        LocalDateTime fecha = calcularFechaSinFestivo();

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conOdontologo(null)
                        .conFechaYHora(fecha)
                        .conEspecialista(true)
                        .conValor(100000.)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El odontologo no existe");
    }

    @Test
    void reconstruirCitaSinFechaYHoraDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFechaYHora(null)
                        .conEspecialista(true)
                        .conValor(100000.)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La fecha y hora son requeridos para crear una cita");
    }

    @Test
    void reconstruirCitaSinEspecialistaDeberiaLanzarError() {
        LocalDateTime fecha = calcularFechaSinFestivo();

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFechaYHora(fecha)
                        .conEspecialista(null)
                        .conValor(100000.)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El especialista es requerido para crear una cita");
    }

    @Test
    void reconstruirCitaSinValorDeberiaLanzarError() {
        LocalDateTime fecha = calcularFechaSinFestivo();

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conFechaYHora(fecha)
                        .conEspecialista(true)
                        .conValor(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El valor es requerido para crear una cita");
    }

    @Test
    void reconstruirCitaSinEstadoDeberiaLanzarError() {
        LocalDateTime fecha = calcularFechaSinFestivo();

        BasePrueba.assertThrows(() -> new CitaTestDataBuilder().conCitaPorDefecto()
                        .conEstado(null)
                        .conFechaYHora(fecha)
                        .conEspecialista(true)
                        .conValor(100000.)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El estado es requerido para crear una cita");
    }


    public static LocalDateTime calcularFechaSinFestivo() {
        LocalDateTime fecha = LocalDateTime.now();
        Boolean auxiliarCalcularFecha = true;
        while (auxiliarCalcularFecha) {
            if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fecha = fecha.plusDays(1);
            } else {
                auxiliarCalcularFecha = false;
            }
        }
        return fecha;
    }

    public static LocalDateTime calcularFechaConFestivo() {
        LocalDateTime fecha = LocalDateTime.now();
        Boolean auxiliarCalcularFecha = true;
        while (auxiliarCalcularFecha) {
            if (!(fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                fecha = fecha.plusDays(1);
            } else {
                auxiliarCalcularFecha = false;
            }
        }
        return fecha;
    }
}
