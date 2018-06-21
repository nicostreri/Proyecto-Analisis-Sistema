package prode;

import org.javalite.activejdbc.Model;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Score extends Model {

	static{
		validateNumericalityOf("amount_points", "correct_predicted_matches").greaterThan(-1).onlyInteger().message("Cantidad de puntos incorrecta");
		//validateNumericalityOf("partidos_acertados").greaterThan(0).onlyInteger().message("Cantidad de Partidos Acertados incorrecta");
	}


	/*
		Retorna la lista de las prediciones por las cuales obtuvo la puntuacion
	*/
	public List<Prediction> obtenerListaPredicciones(){
		List<Prediction> predicciones = Prediction.find("score_id= ?", this.get("id"));
		return predicciones;
	}
    
    public Bet obtenerBet(){
        return Bet.findFirst("id= ?",this.get("bet_id"));
    }
    
    public String getPoints(){
        return this.getString("amount_points");
    }
    
    public String getId(){
        return this.getString("id");        
    }

}
