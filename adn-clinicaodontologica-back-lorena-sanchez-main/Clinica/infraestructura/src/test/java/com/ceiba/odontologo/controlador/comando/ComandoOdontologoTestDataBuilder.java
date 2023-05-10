package com.ceiba.odontologo.controlador.comando;


import com.ceiba.odontologo.comando.ComandoCrearOdontologo;

public class ComandoOdontologoTestDataBuilder {

    private String id;
    private String nombre;
    private String apellido;
    private String matricula;

    public ComandoOdontologoTestDataBuilder crearPorDefecto() {
        this.id = "1";
        this.nombre = "Lorena";
        this.apellido = "Ocampo";
        this.matricula = "1234";
        return this;
    }

    public ComandoCrearOdontologo crear() {
        return new ComandoCrearOdontologo(nombre, apellido, matricula);
    }

    public ComandoOdontologoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}
