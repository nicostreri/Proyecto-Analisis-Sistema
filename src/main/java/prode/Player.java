package prode;

import org.javalite.activejdbc.Model;
import java.util.List;

import org.javalite.activejdbc.annotations.*;
@IdName("username")
public class Player extends Model {
	static{
		validatePresenceOf("username").message("Ingrese el nombre del usuario");
	}
	//cambia el nombre del usuario
	public void setUsername(String nombre){ 
		set("username", nombre);
	}
	//obtengo el nombre del usuario
	public String getUsername(){ 
		return (String) get("username");
	}
	   
	public Integer  getPoint(){ //obtengo la cantidad de puntos totales
		Integer suma = 0;
		
		List<Score> puntuacion = Score.where("username_player= ?", this.getUsername());

		for(Score temp : puntuacion){
			suma += (Integer)temp.get("total_score");
		}
		return suma;	
	}

    public List<Score> obtenerListaScores(){
        List<Score> scores = Score.find("username_player= ?", this.getUsername());
        return scores;
    }
    
}
