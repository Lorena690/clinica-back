UPDATE cita
	SET id_odontologo=:idodontologo, fechayhora=:fechayhora, especialista=:especialista, estado=:estado, valor=:valor
	WHERE id= :id;