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
		res.body("ok");
		return null;

		/*
			NOTA: Si es el ultimo partido de la fecha.
			Calcular la Fecha
		*/
	};
	
}
