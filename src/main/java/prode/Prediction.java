package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class Prediction extends Model {
	static{
		validateWith(new PredictionValidator()).message("ingrese el tipo");
		validateNumericalityOf("visit_goals", "local_goals").greaterThan(-1).onlyInteger().message("Cantidad de puntos incorrecta");
	}
	public void setTipo(String nuevoTipo){ //cambia el tipo_result
		setString("type_result", nuevoTipo);
	}
	
	public void setCantGV(Integer cantV){ //cambia la goles como visitante	
		setInteger("visit_goals", cantV); }
	
	public Integer getCantGV(){ //obtiene la cantidad de goles como visitante
		return getInteger("visit_goals");
	}	
	
	public void setCantGL(Integer cantL){ //cambia los goles locales	
		setInteger("local_goals", cantL); }

	public Integer getCantGL(){ //obtiene la cantidad de goles como local
		return getInteger("local_goals");
	}	

	public void setPuntos(int puntos){ //modificar los puntos del usuario
		if(puntos < -1) puntos = -1;
		setInteger("points_earned", puntos); }
	
	public int getPuntos(){ //obtiene los puntos dado un bet y un result
		return getInteger("points_earned");
	}	
		
	
}
