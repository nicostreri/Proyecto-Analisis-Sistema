package prode;

import org.javalite.activejdbc.Model;
import java.util.List;

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
}
