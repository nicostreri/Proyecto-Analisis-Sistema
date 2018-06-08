package prode;

import org.javalite.activejdbc.Base;

import prode.User;
import static spark.Spark.*;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import org.json.*;

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
    		if(!Base.hasConnection()){ 
    			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode?nullNamePatternMatchesAll=true", "root", "root");	
    		}
    	});

		after("*", (req, res) ->{
			if(Base.hasConnection()){
				Base.close();
			}
		});

		get("/login", (req, res) -> {
			if(Util.userLogeado(req)){
	    		res.redirect("/perfil");
	    		return null;
	    	}
	    	return new ModelAndView(null, "./views/login.mustache");
	        }, new MustacheTemplateEngine()
	    );

	    post("/login", (req, res) -> {
	    	//Procesamiento de Login
	    	boolean loginCorrecto = false;
	    	String error = "";
	    	if(Util.userLogeado(req)){
	    		loginCorrecto = true;
	    	}else{
		    	
		    	String user = req.queryParams("username");
		    	String pass = req.queryParams("claveinput");
		    	if(user != null && pass!= null && !user.equals("") && !pass.equals("")){
		    		if(User.logear(user,pass)!=null){
		    			req.session().attribute("logeado", true);
		    			req.session().attribute("username", user);
		    			loginCorrecto = true;
		    		}else{
		    			error = "Los datos son incorrectos";
		    		}
		    	}else{
		    		error = "Complete todos los datos";
		    	}
		    }
	    	if(loginCorrecto){
	    		res.redirect("/perfil");
	    	}else{
	    		res.status(401);
	    		Map<String,String> r = new HashMap();
	    		r.put("error", error);
	    		return new ModelAndView(r, "./views/login.mustache");
	    	}
	    	return null;
	    }, new MustacheTemplateEngine());

	    get("/salir", (req, res) -> {
			req.session().removeAttribute("logeado");
	    	res.redirect("/login");
	    	return null;
	    });

		get("/", (req, res) -> {
	    	return new ModelAndView(null, "./views/index.mustache");
	        }, new MustacheTemplateEngine()
	    );

		get("/fixture", (req, res) -> {
	    	//Se obtiene los Fixtures con el id y el nombre
	    	List<Map<String,String>> fixtures = new ArrayList();
	    	List<Fixture> temp = Fixture.find("*");
	    	for(Fixture t : temp){
	    		//Por cada Fixture
	    		fixtures.add(t.getDatos());
	    	}
	    	Map respuesta = new HashMap();
	    	respuesta.put("hay_elem",fixtures);
	    	return new ModelAndView(respuesta, "./views/listFixtures.mustache");

	        }, new MustacheTemplateEngine()
	    );

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

	    post("/fecha/:id", (req,res) ->{
	    	/*
				Pasos para lograr una apuesta
				-Debe Existir un Usuario Logeado
				-Validar si es una Fecha Abierta para Apuestas
				-El Usuario debe estar suscripto al Fixture al q pertenece la Fecha
				-Determinar si se cargo un resultado para cada partido de la fecha
				-Crear la Apuesta y Generar una prediccion por cada resultado de partido
	    	*/
			if(Util.fechaAbierta(req.params(":id"))){
				if(Util.userLogeado(req)){
					Schedule temp = Schedule.findById(req.params(":id"));
					if(Util.userSuscripto(req.session().attribute("username"), temp.obtenerFixturePerteneciente().getString("id"))){
						JSONObject obj = new JSONObject(req.body());
						res.body(Util.apuestaValida(obj,req.params(":id")));
					}else{
						res.body("Error: Debe estar suscripto al Fixture.");
					}
				}else{
					res.body("Error: Debe ingresar al Sistema.");
				}
			}else{
				res.body("Error: Esta fecha esta bloqueada.");
			}
	    	return null;
	    });
 
	    get("/fecha/:id", (req, res) -> {
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