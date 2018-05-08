package prode;

import org.javalite.activejdbc.Model;
import java.util.List;

public class Schedule extends Model {
	static{
		validatePresenceOf("nombre_fecha").message("Ingrese nombre del Equipo");
	}

	public Fixture obtenerFixturePerteneciente(){
		Fixture temp = Fixture.findFirst("id=?", this.get("id_fixture"));
		return temp;
	}


	/*
		Retorna la lista de los Partidos que pertenen a la Fecha
	*/
	public List<Match> obtenerListaPartidos(){
		List<Match> partidos = Match.find("id_schedule= ?", this.get("id"));
		return partidos;
	}

}