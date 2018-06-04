package prode;

import org.javalite.activejdbc.Model;
import java.util.Date;
import java.util.List;

public class Bet extends Model {
	 static{	
			dateFormat("yyyy-MM-dd HH:mm:ss","bet_date");//FORMATO: 'YYYY-MM-DD HH:MM:SS'
    	validatePresenceOf("bet_date").message("Ingrese bet_date");
  	}
		//Almacena una fecha
		public void setFecha(String fecha){
				setDate("bet_date",fecha);
		}
		//Retorna una fecha en formato Date (Observar)
		public Date getFecha(){
			return getDate("bet_date");
		}	
		//Retorna el Schedule en el cual se realizo la apuesta
		public Schedule obtenerSchedule(){
			Schedule temp = Schedule.findFirst("id=?", this.get("schedule_id"));
			return temp;
		}
		//Retorna el Player que realizo la apuesta
		public Player obtenerPlayer(){
			Player temp = Player.findFirst("username=?", this.get("username_player"));
			return temp;
		}
		//Retorna la lista de todos los resultados relacionados con la apuesta
		public List<Result> obtenerResultados(){
			List<Result> temp = this.getAll(Result.class);
			return temp;
		}
}
