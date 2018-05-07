package prode;

import org.javalite.activejdbc.Model;

public class Schedule extends Model {
	static{
		validatePresenceOf("nombre_fecha").message("Ingrese nombre del Equipo");
	}

	public Fixture obtenerFixturePerteneciente(){
		Fixture temp = Fixture.findFirst("id=?", this.get("id_fixture"));
		return temp;
	}

}