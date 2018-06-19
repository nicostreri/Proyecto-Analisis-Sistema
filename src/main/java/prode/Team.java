package prode;
import java.util.*;
import org.javalite.activejdbc.Model;

public class Team extends Model {
	static{
		validatePresenceOf("name").message("Ingrese nombre del Equipo");
	}

	public Map<String,String> getDatos(){
		Map<String,String> resp = new HashMap();
		resp.put("id",this.getString("id"));
		resp.put("name",this.getString("name"));
		return resp;
	}
}
