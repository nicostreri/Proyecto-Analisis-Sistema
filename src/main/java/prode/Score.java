package prode;

import org.javalite.activejdbc.Model;
import java.util.List;

public class Score extends Model {

	static{
		validateNumericalityOf("cant_puntos", "partidos_acertados").greaterThan(-1).onlyInteger().message("Cantidad de puntos incorrecta");
		//validateNumericalityOf("partidos_acertados").greaterThan(0).onlyInteger().message("Cantidad de Partidos Acertados incorrecta");
	}


	/*
		Retorna la lista de las prediciones por las cuales obtuvo la puntuacion
	*/
	public List<Prediction> obtenerListaPredicciones(){
		List<Prediction> predicciones = Prediction.find("id_score= ?", this.get("id"));
		return predicciones;
	}
}