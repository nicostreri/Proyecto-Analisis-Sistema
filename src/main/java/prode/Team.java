package prode;

import org.javalite.activejdbc.Model;

public class Team extends Model {
	static{
		validatePresenceOf("nombre").message("Ingrese nombre del Equipo");
	}
}