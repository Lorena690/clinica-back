package com.ceiba.odontologo.controlador.comando;


import com.ceiba.odontologo.comando.ComandoModificarOdontologo;

public class ComandoModificarOdontologoTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public ComandoModificarOdontologoTestDataBuilder crearPorDefecto() {
        this.id = 1L;
        this.nombre = "Lorena";
        this.apellido = "Ocampo";
        this.matricula = "1234";
        return this;
    }

    public ComandoModificarOdontologo crear() {
        return new ComandoModificarOdontologo(id, nombre, apellido, matricula);
    }

    public ComandoModificarOdontologoTestDataBuilder conId(Long valor) {
        this.id = valor;
        return this;
    }
}
