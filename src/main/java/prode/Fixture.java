package prode;
import org.javalite.activejdbc.*;
import org.javalite.activejdbc.Model;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.javalite.activejdbc.annotations.*;
@Many2Many(other = Player.class, join = "players_fixtures", sourceFKName = "fixture_id", targetFKName = "player_username")
public class Fixture extends IGetDatos{
	static{
		validatePresenceOf("name").message("Ingrese el nombre del fixture");
	}
	//Retorna la lista de Schedules que pertenecen al fixture
	public List<Schedule> obtenerListaSchedules(){
		List<Schedule> temp = Schedule.find("fixture_id= ?", this.get("id"));
		return temp;
	}
  //Retorna la lista de Players subscritos al Fixture
	public List<Player>  obtenerListaPlayer(){
		//Fixture fix = Fixture.findById(this.get("id"));
		//List<Player> temp = this.getAll(Player.class);
		//return temp;
		return this.getAll(Player.class);
	}

	public Map<String,String> getDatos(){
		Map<String,String> resp = new HashMap();
		resp.put("id",this.getString("id"));
		resp.put("name",this.getString("name"));
		return resp;
	}

    public String getName(){
        return this.getString("name");
    }
}
