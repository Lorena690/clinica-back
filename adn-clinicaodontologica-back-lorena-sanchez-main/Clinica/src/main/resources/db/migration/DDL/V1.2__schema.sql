CREATE TABLE cita(
    id SERIAL PRIMARY KEY,
    id_paciente SERIAL NOT NULL,
    id_odontologo SERIAL NOT NULL,
    fechayhora TIMESTAMP NOT NULL,
    especialista BOOLEAN NOT NULL,
    estado VARCHAR(255) NOT NULL,
    valor decimal (10,2) NOT NULL,
    CONSTRAINT fk_cita_paciente
        FOREIGN KEY(id_paciente)
        REFERENCES paciente(id),
    CONSTRAINT fk_cita_odontologo
        FOREIGN KEY(id_odontologo)
        REFERENCES odontologo(id)

);

