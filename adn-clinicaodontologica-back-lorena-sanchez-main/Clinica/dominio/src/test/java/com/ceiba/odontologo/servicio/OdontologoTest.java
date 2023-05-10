package com.ceiba.odontologo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.odontologo.modelo.dto.OdontologoDTO;
import com.ceiba.odontologo.modelo.dto.OdontologoDTOTestDataBuilder;
import com.ceiba.odontologo.modelo.entidad.Odontologo;
import com.ceiba.odontologo.modelo.entidad.OdontologoTestDataBuilder;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologo;
import com.ceiba.odontologo.modelo.entidad.SolicitudModificarOdontologoTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OdontologoTest {

    @Test
    void deberiaCrearOdontologoExitoso() {

        Odontologo odontologo = new OdontologoTestDataBuilder()
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .crear();

        Assertions.assertEquals("Lorena", odontologo.getNombre());
        Assertions.assertEquals("Sanchez", odontologo.getApellido());
        Assertions.assertEquals("1234", odontologo.getMatricula());

    }

    @Test
    void crearOdontologoSinApellidoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conNombre("Lorena")
                        .conMatricula("1234")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El apellido es requerido para crear un odontologo");
    }

    @Test
    void crearOdontologoSinNombreDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conApellido("Sanchez")
                        .conMatricula("1234")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El nombre es requerido para crear un odontologo");
    }

    @Test
    void crearOdontologoSinMatriculaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "La matricula es requerida para crear un odontologo");
    }

    @Test
    void deberiaModificarOdontologoExitoso() {

        Odontologo odontologo = new OdontologoTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .modificar();

        Assertions.assertEquals(1L, odontologo.getId());
        Assertions.assertEquals("Lorena", odontologo.getNombre());
        Assertions.assertEquals("Sanchez", odontologo.getApellido());
        Assertions.assertEquals("1234", odontologo.getMatricula());
    }

    @Test
    void modificarOdontologoSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conMatricula("1234")
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "El Id es requerido para modificar un odontologo");
    }

    @Test
    void deberiaReconstruirOdontologoExitoso() {

        Odontologo odontologo = new OdontologoTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .reconstruir();

        Assertions.assertEquals("Lorena", odontologo.getNombre());
        Assertions.assertEquals("Sanchez", odontologo.getApellido());
        Assertions.assertEquals("1234", odontologo.getMatricula());

    }

    @Test
    void reconstruirOdontologoSinApellidoDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conId(1L)
                        .conNombre("Lorena")
                        .conMatricula("1234")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El apellido es requerido para crear un odontologo");

    }

    @Test
    void reconstruirOdontologoSinNombreDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conId(1L)
                        .conApellido("Sanchez")
                        .conMatricula("1234")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El nombre es requerido para crear un odontologo");

    }

    @Test
    void reconstruirOdontologoSinIdDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conMatricula("1234")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El Id es requerido para crear un odontologo");

    }

    @Test
    void reconstruirOdontologoSinMatriculaDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new OdontologoTestDataBuilder()
                        .conId(1L)
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La matricula es requerida para crear un odontologo");

    }

    @Test
    void deberiaCrearOdontologoDTOExitoso() {

        OdontologoDTO odontologo = new OdontologoDTOTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .crear();

        Assertions.assertEquals("Lorena", odontologo.getNombre());
        Assertions.assertEquals("Sanchez", odontologo.getApellido());
        Assertions.assertEquals("1234", odontologo.getMatricula());
        Assertions.assertEquals(1L, odontologo.getId());
    }

    @Test
    void deberiaCrearSolicitudModificarOdontologoExitoso() {

        SolicitudModificarOdontologo odontologo = new SolicitudModificarOdontologoTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conMatricula("1234")
                .crear();

        Assertions.assertEquals("Lorena", odontologo.getNombre());
        Assertions.assertEquals("Sanchez", odontologo.getApellido());
        Assertions.assertEquals("1234", odontologo.getMatricula());
        Assertions.assertEquals(1L, odontologo.getId());
    }
}
