package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;
import org.json.*;

public class ApiController{
	public static Route listarFixture = (req, res) -> {
		//Se obtiene los Fixtures con el id y el nombre
	    List<Fixture> temp = Fixture.find("*");
	    JSONArray resp = new JSONArray();
	    for(Fixture t : temp){
	    	//Por cada Fixture
	    	resp.put(new JSONObject(t.getDatos()));
	    }
	    res.body(resp.toString());
	    res.type("application/json");
	    return null;
	};
	
	//Obtiene las fechas pertenecientes a un Fixture
	public static Route listarFecha = (req, res) -> {
		List<Schedule> temp = Schedule.find("fixture_id= ?",req.params(":idFix"));
		JSONArray resp = new JSONArray();
	    for(Schedule t : temp){
	    	//Por cada Schedule
	    	resp.put(new JSONObject(t.getDatos()));
	    }
	    res.body(resp.toString());
	    res.type("application/json");
	    return null;
	};
    
    //Obtiene los partidos pertenecientes a un Schedule
	public static Route listarPartido = (req, res) -> {
		List<Match> tempM = Match.find("schedule_id= ?",req.params(":idFecha"));
		JSONArray resp = new JSONArray();
	    for(Match t : tempM){
	    	//Por cada Match
	    	resp.put(new JSONObject(t.getDatos()));
	    }
	    res.body(resp.toString());
	    res.type("application/json");
	    return null;
	};

	public static Route cargarResultado = (req, res) ->{
																								//HACER SOLO SI EL PARTIDO YA SE JUGO
		Match temp = Match.findById(req.params(":idPartido"));
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
	
	public static Route listarEquipos = (req, res) -> {
		//Se obtiene los Fixtures con el id y el nombre
	    List<Team> temp = Team.find("*");
	    JSONArray resp = new JSONArray();
	    for(Team t : temp){
	    	//Por cada Fixture
	    	resp.put(new JSONObject(t.getDatos()));
	    }
	    res.body(resp.toString());
	    res.type("application/json");
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
}
