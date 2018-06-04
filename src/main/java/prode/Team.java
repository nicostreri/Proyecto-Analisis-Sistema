package prode;

import org.javalite.activejdbc.Model;

public class Team extends Model {
	static{
		validatePresenceOf("name").message("Ingrese nombre del Equipo");
	}
}
