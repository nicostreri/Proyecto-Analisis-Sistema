package prode;
import java.util.*;
import spark.*;
import org.json.*;
import java.text.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.javalite.activejdbc.Base;

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

	public static boolean apuestaValida(JSONObject apuesta, String idFecha){
		String[] partidosApostados = JSONObject.getNames(apuesta);
		//Se obtienen los partidos asociados a la fecha
		Schedule fecha = Schedule.findById(idFecha);
		if(fecha == null)return false;
		List<Match> partidos = fecha.obtenerListaPartidos();

		//Se determina si se dio una apuesta valida para cada partido
		for(Match ec : partidos){
			String key = "p" + ec.getString("id");
			if(!pertenece(partidosApostados, key))return false;
			int estado = -2;
			try{
				estado = apuesta.getInt(key);
			}catch (Exception e){}
			if(estado<-1 || estado>1)return false;
		}
		return true;		
	}

	private static boolean pertenece(String[] elems, String e){
		if(elems==null)return false;
		for(String a : elems){
			if(a.equals(e))return true;
		}
		return false;
	}

	/**
	 * @pre apuestaValida(apuesta, idFecha) == TRUE
	 */
	public static String registrarApuesta(JSONObject apuesta, String idFecha, String user){
		Base.openTransaction();
		try{
			Bet nueva = new Bet();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");String s = formatter.format(new Date());
			nueva.setFecha(s);
			nueva.setString("schedule_id",idFecha);
			nueva.setString("username_player",user);
			nueva.saveIt();

			//Se guarda la apuesta a cada partido
			Iterator<String> it = apuesta.keys();
			while(it.hasNext()){
				//Por cada Resultado Apostado, se crea una predicion
				//String idPart = apuesta.getString(it.next()).substring(1);
				String key = it.next();
				String idPart = key.substring(1);
				int apu = apuesta.getInt(key);
				Prediction pNew = new Prediction();
				pNew.setTipo(obtenerTipo(apu));
				pNew.saveIt();
				BetsResults bR = new BetsResults();
				bR.setInteger("bet_id", nueva.getId());
				bR.setString("result_id", idPart);
				bR.setParent(pNew);
				bR.saveIt();
			}
			Base.commitTransaction();
			return "";
		}catch (Exception e) {
			Base.rollbackTransaction();
		}
		return null;
	}

	private static String obtenerTipo(int t){
		if(t==-1)return "gana_local";
		if(t== 0)return "empate";
		if(t== 1)return "gana_visitante";
		return null;
	}
}