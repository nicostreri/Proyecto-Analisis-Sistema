package prode;

import org.javalite.activejdbc.Model;
import java.util.Date;

public class Match extends Model {

	static{
			dateFormat("yyyy-MM-dd HH:mm:ss","fecha");
    	validatePresenceOf("fecha").message("Proveer fecha");
  		//validateRegexpOf("fecha","\\b([0-9])\\w+\\b").message("Formato de fecha incorrecto.Numeros y guion - ");
  }//FORMATO: 'YYYY-MM-DD HH:MM:SS' Agregar guion,espacio y los 2 puntos.

		//Almacena una fecha
		public void setFecha(String fecha){
				setDate("fecha",fecha);
		}
		//Retorna una fecha en formato Date (Observar)
		public Date getFecha(){
			return getDate("fecha");
		}
		
		//Retorna el Schedule en donde se encuentra el match
		public Schedule obtenerSchedule(){
			Schedule temp = Schedule.findFirst("id=?", this.get("id_schedule"));
			return temp;
		}
		
		//Retorna el equipo local
		public Team obtenerTeamLocal(){
			Team temp = Team.findFirst("id=?", this.get("id_team_local"));
			return temp;
		}

		//Retorna el equipo visitante
		public Team obtenerTeamVisitante(){
			Team temp = Team.findFirst("id=?",this.get("id_team_visitante"));
			return temp;
		}

		//Retorna el resultado del partido
		public Result obtenerResultado(){
			Result temp = Result.findFirst("id=?",this.get("id_result"));
			return temp;
		}
			
}
