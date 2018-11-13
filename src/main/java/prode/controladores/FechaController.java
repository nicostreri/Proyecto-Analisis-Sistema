package prode.controladores;

import prode.*;
import prode.exceptions.*;
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
		if(!Util.fechaAbierta(idFecha)){
			throw new ApuestaFechaException("Esta fecha esta bloqueada.");
		}
		Schedule temp = Schedule.findById(idFecha);
		if(!Util.userSuscripto(req.session().attribute("username"), temp.obtenerFixturePerteneciente().getString("id"))){
			throw new ApuestaFechaException("Debe estar suscripto al Fixture.");
		}
		
		JSONObject obj = null;
		try{
			obj = new JSONObject(req.body());
		}catch (Exception e) {
			throw new ApuestaFechaException("El cuerpo de la Peticion es Incorrecto.");
		}
		if(obj != null){
			if(Util.apuestaValida(obj,idFecha)){
				//El usuario ya aposto??
				Bet betRea = Bet.findFirst("schedule_id=? and username_player=?", idFecha, req.session().attribute("username"));
				res.body( (betRea != null)? ("Error: Ya aposto a la Fecha.") : (Util.registrarApuesta(obj, idFecha, req.session().attribute("username"))) );
						
			}else{
				throw new ApuestaFechaException("La apuesta no es correcta");
			}
		}
	    return null;
	};

	/**
     * Calculas las Apuestas realizadas a una Fecha. Le otroga los puntos correspondientes a cada User que aposto
	 */
	public static Route calcularFecha = (req, res) -> {
		String idFecha = req.params(":idFecha");
		Base.openTransaction();
		try{
			if(idFecha != null){
				Schedule f = Schedule.findById(idFecha);
				if(Util.fechaCerrada(f)){
					if(!f.getBoolean("calculated")){
						//La fecha No fue calculada y ademas tiene todos sus partidos con un resultado cargado
						// se procede a Calcular las Apuestas a esta fecha y entregar los correspondientes puntos a sus jugadores
						List<Match> part = f.obtenerListaPartidos();
						List<Bet> listaApuestas = Bet.find("schedule_id= ? ",f.getId());
						for(Bet ec : listaApuestas){
							//Por cada apuesta realizada
							Score scoreObtenido = new Score(); 
							scoreObtenido.setPoints(0);
							scoreObtenido.setPartidosHit(0);
							scoreObtenido.saveIt();
							int puntos = 0;
							int cantPartidosAcertados = 0;
							List<BetsResults> results = BetsResults.find("bet_id = ? ", ec.getId());

							for(BetsResults r : results){
								//Comparo el Resultado con la Apuesta
								Prediction p = r.getPrediction();
								Result re = r.getResult();
								boolean hit = p.getTipo().equals(re.getTipo());
								if(hit){
									puntos += 10; //Puntos por partido Acertado
									cantPartidosAcertados++;
								}
								p.set("score_id", scoreObtenido.getId());
								//p.setParent(scoreObtenido);
								p.setHit(hit);
								p.saveIt();
							}
							if(cantPartidosAcertados == results.size())puntos += 500; //Bonus: Acerto toda la Fecha
							if(cantPartidosAcertados != results.size() && cantPartidosAcertados >= (50*results.size())/100)puntos+=200; //Bonus: Acertar mas o igual a la Mitad de los Partidos

							scoreObtenido.setPartidosHit(cantPartidosAcertados);
							scoreObtenido.setPoints(puntos);
							scoreObtenido.setBet(ec);
							scoreObtenido.setString("username_player", ec.getString("username_player"));
							scoreObtenido.saveIt();
						}
						f.setBoolean("calculated", true);
						f.saveIt();
						res.body("Fecha Calculada");
					}else{
						res.body("Error: La fecha ya fue calculada.");
					}
				}else{
					res.body("Error: La fecha contiene partidos sin resultados.");
				}
			}
			Base.commitTransaction();
		}catch (Exception e){
			Base.rollbackTransaction();
			res.status(500);
			res.body(e.getMessage());
		}
		return null;
	};
}
