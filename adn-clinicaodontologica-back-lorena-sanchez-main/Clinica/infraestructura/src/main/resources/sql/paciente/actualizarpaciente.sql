UPDATE paciente
	SET nombre=:nombre, apellido=:apellido, estrato=:estrato, documento=:documento, domicilio=:domicilio
	WHERE id=:id;