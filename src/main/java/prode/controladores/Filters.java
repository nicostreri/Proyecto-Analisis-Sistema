package prode.controladores;

import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;

public class Filters{

	public static Filter preGeneral = (req, res) -> {
		Metrics.requestsPet.mark();
    	if(!Base.hasConnection()){ 
    		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode?nullNamePatternMatchesAll=true", "root", "root");	
    	}
    };

	public static Filter posGeneral = (req, res) ->{
		if(Base.hasConnection()){
			Base.close();
		}
	};

	public static Filter seccionProtegida = (request, response) -> {
	    if(!Util.userLogeado(request)){
	    	halt(401, "Ingrese al Sistema antes de continuar.");
	    }
	};

	public static Filter seccionAdmin = (request, response) -> {
	    if(request.session().attribute("isAdmin") == null){
	    	halt(401, "Ingrese como Administrador antes de continuar.");
	    }
	};

}