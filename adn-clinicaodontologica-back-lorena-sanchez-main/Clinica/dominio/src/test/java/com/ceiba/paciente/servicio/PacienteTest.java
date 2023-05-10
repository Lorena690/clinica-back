package com.ceiba.paciente.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.modelo.dto.PacienteDTO;
import com.ceiba.paciente.modelo.dto.PacienteDTOTestDataBuilder;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.PacienteTestDataBuilder;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPaciente;
import com.ceiba.paciente.modelo.entidad.SolicitudModificarPacienteTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PacienteTest {

    @Test
    void deberiaCrearPacienteExitoso() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .crear();

        Assertions.assertEquals("Lorena", paciente.getNombre());
        Assertions.assertEquals("Sanchez", paciente.getApellido());
        Assertions.assertEquals("1", paciente.getEstrato());
        Assertions.assertEquals("1234", paciente.getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", paciente.getDomicilio());
    }

    @Test
    void crearPacienteSinApellidoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conNombre("Lorena")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El apellido es requerido para crear un paciente");
    }

    @Test
    void crearPacienteSinNombreDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El nombre es requerido para crear un paciente");
    }

    @Test
    void crearPacienteSinEstratoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El estrato es requerido para crear un paciente");
    }

    @Test
    void crearPacienteSinDomicilioDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El domicilio es requerido para crear un paciente");
    }

    @Test
    void crearPacienteSinDocumentoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El documento es requerido para crear un paciente");
    }

    @Test
    void deberiaModificarPacienteExitoso() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .modificar();

        Assertions.assertEquals(1L, paciente.getId());
        Assertions.assertEquals("Lorena", paciente.getNombre());
        Assertions.assertEquals("Sanchez", paciente.getApellido());
        Assertions.assertEquals("1", paciente.getEstrato());
        Assertions.assertEquals("1234", paciente.getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", paciente.getDomicilio());
    }

    @Test
    void modificarPacienteSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .modificar(),
                ExcepcionValorObligatorio.class,
                "El Id es requerido para modificar un paciente");
    }

    @Test
    void deberiaReconstruirPacienteExitoso() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .reconstruir();

        Assertions.assertEquals("Lorena", paciente.getNombre());
        Assertions.assertEquals("Sanchez", paciente.getApellido());
        Assertions.assertEquals("1", paciente.getEstrato());
        Assertions.assertEquals("1234", paciente.getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", paciente.getDomicilio());
    }

    @Test
    void reconstruirPacienteSinApellidoDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conId(1L)
                        .conNombre("Lorena")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El apellido es requerido para crear un paciente");

    }

    @Test
    void reconstruirPacienteSinIdDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conNombre("Lorena")
                        .conEstrato("1")
                        .conApellido("Sanchez")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El Id es requerido para crear un paciente");

    }

    @Test
    void reconstruirPacienteSinEstratoDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conId(1L)
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El estrato es requerido para crear un paciente");

    }

    @Test
    void reconstruirPacienteSinNombreDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conId(1L)
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El nombre es requerido para crear un paciente");

    }

    @Test
    void reconstruirPacienteSinDocumentoDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conId(1L)
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDomicilio("Calle 00 # 00 - 11")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El documento es requerido para crear un paciente");

    }

    @Test
    void reconstruirPacienteSinDomicilioDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new PacienteTestDataBuilder()
                        .conId(1L)
                        .conNombre("Lorena")
                        .conApellido("Sanchez")
                        .conEstrato("1")
                        .conDocumento("1234")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El domicilio es requerido para crear un paciente");

    }

    @Test
    void crearPacienteDTODeberiaSerExitoso() {

        PacienteDTO paciente = new PacienteDTOTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .crear();

        Assertions.assertEquals(1L, paciente.getId());
        Assertions.assertEquals("Lorena", paciente.getNombre());
        Assertions.assertEquals("Sanchez", paciente.getApellido());
        Assertions.assertEquals("1", paciente.getEstrato());
        Assertions.assertEquals("1234", paciente.getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", paciente.getDomicilio());

    }

    @Test
    void crearSolicitudModificarPacienteDeberiaSerExitoso() {

        SolicitudModificarPaciente paciente = new SolicitudModificarPacienteTestDataBuilder()
                .conId(1L)
                .conNombre("Lorena")
                .conApellido("Sanchez")
                .conEstrato("1")
                .conDocumento("1234")
                .conDomicilio("Calle 00 # 00 - 11")
                .crear();

        Assertions.assertEquals(1L, paciente.getId());
        Assertions.assertEquals("Lorena", paciente.getNombre());
        Assertions.assertEquals("Sanchez", paciente.getApellido());
        Assertions.assertEquals("1", paciente.getEstrato());
        Assertions.assertEquals("1234", paciente.getDocumento());
        Assertions.assertEquals("Calle 00 # 00 - 11", paciente.getDomicilio());

    }
}
