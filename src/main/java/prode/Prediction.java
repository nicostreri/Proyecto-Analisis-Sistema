package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class Prediction extends Model {
	static{
		validateWith(new PredictionValidator()).message("ingrese el tipo");
	}
	public void setTipo(String nuevoTipo){ //cambia el tipo_result
		setString("result_type", nuevoTipo);
	}
	
	public void setPuntos(int puntos){ //modificar los puntos del usuario
		if(puntos < -1) puntos = -1;
		setInteger("points_earned", puntos); }
	
	public int getPuntos(){ //obtiene los puntos dado un bet y un result
		return getInteger("points_earned");
	}
}
