package prode;
import java.util.*;
import spark.*;
import org.json.*;

import static spark.Spark.*;
public class Util{
	
	/**
  	 * fechaAbierta
  	 * Determina si es posible apostar a la Fecha, para ello la Fecha del Primer partido debe ser Posterior a Hoy
  	 * @param idFecha : id de la Fecha que se quiere comprobar : String
	*/
	public static boolean fechaAbierta(String idFecha){
		Schedule temp = Schedule.findById(idFecha);
		if(temp != null){
			List<Match> t = temp.obtenerListaPartidos();
			if(t.size()>0){
				if(t.get(0).getFecha().compareTo(new Date()) > 0){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean userLogeado(Request req){
		return req.session().attribute("logeado") != null;
	}

	//@pre: Util.userLogeado() == True
	//@pre: username e idFixture deben existir en la Base de Datos
	public static boolean userSuscripto(String username, String idFixture){
		List<Player> suscritos = Fixture.findById(idFixture).getAll(Player.class);
		for(Player t : suscritos){
			if(t.getUsername().equals(username)){
				return true;
			}
		}
		return false;
	}

	//public static String apuestaValida(JSONObject apuesta, String idFecha){
	//public static boolean apuestaValida(JSONObject apuesta, String idFecha){
		String[] partidosApostados = JSONObject.getNames(apuesta);
		//Se obtienen los partidos asociados a la fecha


		//Se determina si se dio una apusta valida para cada partido


		//Se registran las apuestas



		
	}
}