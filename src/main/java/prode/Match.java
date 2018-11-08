package prode;

import org.javalite.activejdbc.Model;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class Match extends Model {
	static{
			dateFormat("yyyy-MM-dd HH:mm:ss","match_date");//FORMATO: 'YYYY-MM-DD HH:MM:SS'
    	validatePresenceOf("match_date").message("Ingrese fecha del match");
  }
		//Almacena una fecha
		public void setFecha(String fecha){
				setDate("match_date",fecha);
		}
		//Retorna una fecha de jugada en formato Date (Observar)
		public Date getFecha(){
			return getDate("match_date");
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
			return Result.findFirst("id = ?",this.getInteger("result_id"));
		}

		public Map<String,String> getDatos(){
			//(id,match_date,schedule_id,local_team_id,visitor_team_id,result_id)
			Map<String,String> resp = new HashMap();
			resp.put("id",this.getString("id"));
			resp.put("fecha_jugada",this.getDate("match_date").toString());

			//Se obtienen datos del Local
			resp.put("id_local", this.getString("local_team_id"));
			resp.put("name_local", Team.findById(resp.get("id_local")).getString("name"));

			//Se obtienen datos del Visitante
			resp.put("id_visitante", this.getString("visitor_team_id"));
			resp.put("name_visitante", Team.findById(resp.get("id_visitante")).getString("name"));


			Result tResul = Result.findById(this.getString("result_id"));
			if(!tResul.getTipo().equals("no_jugado")){
				//El partido ya se jugo
				resp.put("jugado", "true");
				resp.put("cantGolVisitante", "(" + tResul.getCantGV().toString() + ")");
				resp.put("cantGolLocal", "(" + tResul.getCantGL().toString() + ")");
			}
			return resp;
		}
}
