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
	    return null;
	};
}