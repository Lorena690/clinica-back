package com.ceiba.paciente.controlador.comando;


import com.ceiba.paciente.comando.ComandoCrearPaciente;

public class ComandoPacienteTestDataBuilder {

    private String id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public ComandoPacienteTestDataBuilder crearPorDefecto() {
        this.id = "1";
        this.nombre = "Lorena";
        this.apellido = "Ocampo";
        this.documento = "1234";
        this.estrato = "1";
        this.domicilio = "Calle 00 # 00 - 00";
        return this;
    }

    public ComandoCrearPaciente crear() {
        return new ComandoCrearPaciente(nombre, apellido, estrato, documento, domicilio);
    }

    public ComandoPacienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}
