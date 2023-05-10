package com.ceiba.paciente.controlador.comando;


import com.ceiba.paciente.comando.ComandoModificarPaciente;

public class ComandoModificarPacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String estrato;
    private String documento;
    private String domicilio;

    public ComandoModificarPacienteTestDataBuilder crearPorDefecto() {
        this.id = 1L;
        this.nombre = "Lorena";
        this.apellido = "Ocampo";
        this.documento = "1234";
        this.estrato = "1";
        this.domicilio = "Calle 00 # 00 - 00";
        return this;
    }

    public ComandoModificarPaciente crear() {
        return new ComandoModificarPaciente(id, nombre, apellido, estrato, documento, domicilio);
    }

    public ComandoModificarPacienteTestDataBuilder conId(Long valor) {
        this.id = valor;
        return this;
    }
}
