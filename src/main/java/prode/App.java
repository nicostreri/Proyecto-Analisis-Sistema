package prode;

import org.javalite.activejdbc.Base;

import prode.User;
import static spark.Spark.*;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;


/**
 * Hello world!
 *
 */
public class App{
    
    public static void main( String[] args ){
        
        //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode?nullNamePatternMatchesAll=true", "root", "root");
    	//Base.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode?nullNamePatternMatchesAll=true", "root", "root");
    	port(8081);

    	before("*", (req, res) -> {
    		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode?nullNamePatternMatchesAll=true", "root", "root");	
    	});

		after("*", (req, res) ->{
		    Base.close();
		});

	    get("/fixture/:id", (req, res) -> {
	    		//Se obtiene el Fixture con el id
	    		Fixture temp = Fixture.findById(req.params(":id"));
	    		if(temp != null){
	    			List<Map<String,String>> fechas = new ArrayList();
	    			for(Schedule t : temp.obtenerListaSchedules()){
	    				fechas.add(t.getDatos());
	    			}
	    			Map respuesta = new HashMap();
	    			respuesta.put("hay_elem",fechas);
	    			respuesta.put("name_fixture",temp.getString("name"));
	    			return new ModelAndView(respuesta, "./views/listFechas.mustache");
	    		}
	        	return null;
	        }, new MustacheTemplateEngine()
	    );
 
	    get("/fecha/:id", (req, res) -> {
	    		//Se obtiene la Fecha con el id
	    		Schedule temp = Schedule.findById(req.params(":id"));
	    		if(temp != null){
	    			List<Map<String,String>> partidos = new ArrayList();
	    			for(Match t : temp.obtenerListaPartidos()){
	    				partidos.add(t.getDatos());
	    			}
	    			Map respuesta = new HashMap();
/*
	AQUI
		respuesta.put("posible_apostar", true or false);

		Determinar si es posible o no apostar los resultados de esta fecha
*/


	    			respuesta.put("hay_elem",partidos);
	    			respuesta.put("name_fecha",temp.getString("date_name"));
	    			return new ModelAndView(respuesta, "./views/listPartidos.mustache");
	    		}
	        	return null;
	        }, new MustacheTemplateEngine()
	    );

    	get("/stop", (req,res) -> {
	        new Thread(() -> stop()).start();
	        Base.close();
	        return "Sistema Apagado! :(";
    	});


    	//Control de Errores
    	exception(Exception.class, (exception, request, response) -> {
    		// Handle the exception here
    		response.body( exception.getMessage());
		});
    }
}