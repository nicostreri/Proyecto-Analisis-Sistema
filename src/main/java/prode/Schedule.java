package prode;

import org.javalite.activejdbc.Model;

public class Schedule extends Model {
	static{
		validatePresenceOf("nombre_fecha").message("Ingrese nombre del Equipo");
	}
}