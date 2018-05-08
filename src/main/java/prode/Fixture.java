package prode;
import org.javalite.activejdbc.Model;
import java.util.List;

public class Fixture extends Model{
	static{
		validatePresenceOf("nombre").message("Ingrese el nombre del fixture");
	}
	//Retorna la lista de Schedules que pertenecen al fixture
	public List<Schedule> obtenerListaSchedules(){
		List<Schedule> temp = Schedule.find("id_fixture= ?", this.get("id"));
		return temp;
	}
  //Retorna la lista de Players subscritos al Fixture
	public List<Player>  obtenerListaPlayer(){
		//Fixture fix = Fixture.findById(this.get("id"));
		List<Player> temp = this.getAll(Player.class);
		return temp;
	}
}
