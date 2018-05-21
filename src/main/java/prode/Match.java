package prode;

import org.javalite.activejdbc.Model;
import java.util.Date;

public class Match extends Model {
	static{
			dateFormat("yyyy-MM-dd HH:mm:ss","fecha");//FORMATO: 'YYYY-MM-DD HH:MM:SS'
    	validatePresenceOf("fecha").message("Ingrese fecha del match");
  }
		//Almacena una fecha
		public void setFecha(String fecha){
				setDate("fecha",fecha);
		}
		//Retorna una fecha de jugada en formato Date (Observar)
		public Date getFecha(){
			return getDate("fecha");
		}		
		//Retorna el Schedule en donde se encuentra el match
		public Schedule obtenerSchedule(){
			return Schedule.findFirst("id=?", this.get("schedule_id"));
		}		
		//Retorna el equipo local
		public Team obtenerTeamLocal(){
			return Team.findFirst("id=?", this.get("local_team_id"));
		}
		//Retorna el equipo visitante
		public Team obtenerTeamVisitante(){
			return Team.findFirst("id=?",this.get("visitor_team_id"));			
		}
		//Retorna el resultado del partido
		public Result obtenerResultado(){
			return Result.findFirst("id=?",this.get("result_id"));
		}		
}
