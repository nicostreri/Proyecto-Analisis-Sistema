package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;

public class FixtureController{
	public static TemplateViewRoute listarTodosFixtures = (req, res) -> {
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
	};

     public static TemplateViewRoute listarMisFixture = (req, res) -> {
    return null;
    };    

	public static TemplateViewRoute listarFechasDeFixture =  (req, res) -> {
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
	};
}
