package prode;

import org.javalite.activejdbc.Model;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Schedule extends Model {
	static{
		validatePresenceOf("date_name").message("Ingrese nombre de la fecha");
	}

	public Fixture obtenerFixturePerteneciente(){
		Fixture temp = Fixture.findFirst("id=?", this.get("fixture_id"));
		return temp;
	}

	public Map<String,String> getDatos(){
		Map<String,String> resp = new HashMap();
		resp.put("id",this.getString("id"));
		resp.put("name",this.getString("date_name"));
		return resp;
	}


	/*
		Retorna la lista de los Partidos que pertenen a la Fecha
	*/
	public List<Match> obtenerListaPartidos(){
		List<Match> partidos = Match.find("schedule_id= ?", this.get("id"));
		return partidos;
	}

}
