package prode;
import org.javalite.activejdbc.Model;
import java.util.List;

public class Fixture extends Model{
	static{
		validatePresenceOf("nombre").message("Ingrese el nombre del fixture");
		validateRegexpOf("nombre","\\b([A-Z0-9a-z])\\w+\\b").message("Formato de nombre fixture incorrecto. Mayus/Minus y Numeros");
	}
	
	//Retorna la lista de Schedules que pertenecen al fixture
	public List<Schedule> obtenerListaSchedules(){
		List<Schedule> temp = Schedule.find("id_fixture= ?", this.get("id"));
		return temp;
	}
	
	//Retorna la lista de Players subscritos al Fixture
	//(Ver como obtener los players de una relacion many to many)
	/*public List<Player> obtenerListaPlayer(){
		List<Player> temp = Player.find("id_fixture= ?", this.get("id")); 
	}
	*/
	
  //Retorna la lista de Players subscritos al Fixture
	public List<Player>  obtenerListaPlayer(){
		Fixture fix = Fixture.findById(this.get("id"));
		List<Player> temp = fix.getAll(Player.class);
		return temp;
	}

}
