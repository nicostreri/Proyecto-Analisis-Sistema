package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;
import org.json.*;


public class FechaController{
	public static TemplateViewRoute listarPartidosDeFecha = (req, res) -> {
	    //Se obtiene la Fecha con el id
	    Schedule temp = Schedule.findById(req.params(":id"));
	    if(temp != null){
	    	List<Map<String,String>> partidos = new ArrayList();
	    	boolean primera = true;
	    	Date primeraFecha = null;
	    	for(Match t : temp.obtenerListaPartidos()){
	    		if(primera){
	    			primeraFecha = t.getFecha();
	    			primera = false;
	    		}
	    		partidos.add(t.getDatos());
	    	}
	    	Map respuesta = new HashMap();
	    	if(!primera){
	    		//Existe al menos un partido en la fecha, vemos si es posible apostar
	    		//Para ser posible Hoy debe ser < a la Fecha del Primer Partido
	    		if(primeraFecha.compareTo(new Date()) > 0){
	    			respuesta.put("posible_apostar","true");
	    			if(Util.userLogeado(req)){
	    				respuesta.put("logeado", "true");
	    			}
	    		} 
	    	}
	    	respuesta.put("hay_elem",partidos);
	    	respuesta.put("name_fecha",temp.getString("date_name"));
	    	return new ModelAndView(respuesta, "./views/listPartidos.mustache");
	    }
	    return null;
	};  

	public static Route apostarFecha = (req,res) ->{
	    /*
			Pasos para lograr una apuesta
			-Validar si es una Fecha Abierta para Apuestas
			-El Usuario debe estar suscripto al Fixture al q pertenece la Fecha
			-Determinar si se cargo un resultado para cada partido de la fecha
			-Crear la Apuesta y Generar una prediccion por cada resultado de partido
	    */
		String idFecha = req.params(":id");
		if(Util.fechaAbierta(idFecha)){
			Schedule temp = Schedule.findById(idFecha);
			if(Util.userSuscripto(req.session().attribute("username"), temp.obtenerFixturePerteneciente().getString("id"))){
				res.body("JJ");
				JSONObject obj = null;
				try{
					obj = new JSONObject(req.body());
				}catch (Exception e) {
					res.body("Error: El cuerpo de la Peticion es incorrecto.");
					res.status(400);	
				}
				if(obj != null){
					if(Util.apuestaValida(obj,idFecha)){
						res.body("TODO I");
						//El usuario ya aposto??
						Bet betRea = Bet.findFirst("schedule_id=? and username_player=?", idFecha, req.session().attribute("username"));
						if(betRea != null){
							res.body("Error: Ya aposto a la fecha");
						}else{
							Util.registrarApuesta(obj, idFecha, req.session().attribute("username"));
							
							res.body("Apuesta registrada.");
						}
					}else{
						res.body("Error: La apuesta no es correcta.");
						res.status(400);
					}
				}
			}else{
				res.body("Error: Debe estar suscripto al Fixture.");
			}
		}else{
			res.body("Error: Esta fecha esta bloqueada.");
		}
	    return null;
	};
}
