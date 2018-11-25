package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;
import org.json.*;
import org.javalite.activejdbc.Model;

public class ApiController{

	private static <T extends IGetDatos> String _generarJSONArrayAllIntancesModel(List<T> elem){
	    JSONArray resp = new JSONArray();
	    for(int i=0; i<elem.size(); i++){
	    	resp.put(new JSONObject(elem.get(i).getDatos()));
	    }
	    return resp.toString();
	}

	public static Route listarFixture = (req, res) -> {
		res.body(ApiController._generarJSONArrayAllIntancesModel(Fixture.find("*")));
	    res.type("application/json");
	    return null;
	};

	public static Route listarEquipos = (req, res) -> {
	    res.body(ApiController._generarJSONArrayAllIntancesModel(Team.find("*")));
	    res.type("application/json");
	    return null;
	};

	//Obtiene las fechas pertenecientes a un Fixture
	public static Route listarFecha = (req, res) -> {
	    res.body(ApiController._generarJSONArrayAllIntancesModel(Schedule.find("fixture_id= ?",req.params(":idFix"))));
	    res.type("application/json");
	    return null;
	};
    
    //Obtiene los partidos pertenecientes a un Schedule
	public static Route listarPartido = (req, res) -> {
	    res.body(ApiController._generarJSONArrayAllIntancesModel(Match.find("schedule_id= ?",req.params(":idFecha"))));
	    res.type("application/json");
	    return null;
	};

	public static Route cargarResultado = (req, res) ->{
		Match temp = Match.findById(req.params(":idPartido"));
		if(temp.getFecha().compareTo(new Date()) == 1){
			//No se puede cargar un resultado antes de la Fecha de Juego
			res.body("Error, Partido aun no jugado.");
			return null;
		}
		Result tR = temp.obtenerResultado();
		Integer cantV;
		Integer cantL;
		try{
			cantV = Integer.valueOf(req.queryParams("cantV"));
			cantL = Integer.valueOf(req.queryParams("cantL"));
		}catch (Exception e) {
			return null;
		}
		tR.setCantGV(cantV);
		tR.setCantGL(cantL);
		String tipo = "gana_local";
		if(cantL.intValue() == cantV.intValue()){
			tipo = "empate";
		}else if(cantL.intValue() < cantV.intValue()){
			tipo = "gana_visitante";
		}
		tR.setTipo(tipo);
		tR.saveIt();
		res.body("Resultado Guardado");
		return null;
	};

	public static Route nuevaFecha = (req, res) ->{
		Fixture temp = Fixture.findById(req.params(":idFix"));
		String nombreFecha = req.queryParams("nombreFecha");
		if(temp != null && nombreFecha != null){
			Schedule nF = new Schedule();
			nF.setParent(temp);
			nF.setString("date_name",nombreFecha);
			nF.saveIt();
			res.body("Creado con Exito");
		}
		return null;
	};

	public static Route nuevoFixture = (req, res) ->{
		String nombreFix = req.queryParams("nombreFixture");
		if(nombreFix != null){
			Fixture tF = new Fixture();
			tF.setString("name", nombreFix);
			tF.saveIt();
			res.body("Creado con Exito");
		}
		return null;
	};

	public static Route nuevoEquipo = (req, res) ->{
		String nombreEquipo = req.queryParams("nombreEquipo");
		if(nombreEquipo != null){
			Team tT = new Team();
			tT.setString("name", nombreEquipo);
			tT.saveIt();
			res.body("Creado con Exito");
		}
		return null;
	};

	public static Route nuevoPartido = (req, res) ->{
		String local = req.queryParams("equipoLocal");
		String visit = req.queryParams("equipoVisitante");
		String fecha = req.queryParams("partidoFecha");
		String fId 	 = req.queryParams("fechaPadrePart");

		if(local != null && visit != null && fecha != null && fId !=null && !local.equals(visit)){
			Team tL = Team.findById(local);
			Team tV = Team.findById(visit);
			Schedule f = Schedule.findById(fId);

			if(f.getBoolean("calculated")){
				res.body("Error, Fecha ya calculada. Imposible Modificar.");
				return null;
			}
			fecha += " 00:00:00";
			if(tL != null && tV != null){
				//Los equipos existen y son distintos
				Result result = new Result();
				result.setTipo("no_jugado");
				result.setCantGV(0);
				result.setCantGL(0);
				result.saveIt();

				Match partido = new Match();
				partido.setFecha(fecha);
				partido.setInteger("visitor_team_id", tV.getId());
				partido.setInteger("local_team_id", tL.getId());
				partido.setInteger("result_id", result.getId());
				partido.setInteger("schedule_id", f.getId());
				partido.saveIt();
				res.body("Guardado con exito");
				return null;
			}
		}
		return null;
	};


	/**
	 * Obtiene las estadisticas de las apuestas de un Partido
	 * Retorna:  Porcentaje de Gana Local, % Empate, % Gana Visitante
	*/
	public static Route estadisticaPartido = (req, res) -> {
		String idPartido = req.params(":id");
		Match partido = Match.findById(idPartido);
		Result rPart = partido.obtenerResultado();
		
		List<BetsResults> apuestasAsociadas = BetsResults.find("result_id = ?", rPart.getString("id"));
		int total = apuestasAsociadas.size();
		int local = 0;
		int empate= 0;
		int visit = 0;
		for (BetsResults temp : apuestasAsociadas ) {
			Prediction pTemp = temp.getPrediction();
			switch(pTemp.getTipo()){
                case "gana_local":
                	local++;
                   	break;
                case "gana_visitante":
                	visit++;
                    break;
                case "empate": 
                	empate++;
                    break;      
            }
		}

		Map<String,Integer> resp = new HashMap();
		resp.put("apuestas_totales",total);
		if(total != 0){
			resp.put("gana_local", (local * 100)/total);
			resp.put("gana_visitante",(visit * 100)/total);
			resp.put("empate",(empate * 100)/total);
		}
	    res.body(new JSONObject(resp).toString());
	    res.type("application/json");
	    return null;
	};
}
