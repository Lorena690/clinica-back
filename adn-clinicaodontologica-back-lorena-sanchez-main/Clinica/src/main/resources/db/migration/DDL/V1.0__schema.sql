CREATE TABLE paciente(
    id SERIAL PRIMARY KEY,
    nombre VARCHAR (255) NOT NULL,
    apellido VARCHAR (255) NOT NULL,
    estrato VARCHAR (255) NOT NULL,
    documento VARCHAR (255) NOT NULL,
    domicilio VARCHAR (255) NOT NULL
);

