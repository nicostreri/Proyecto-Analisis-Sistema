package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class Prediction extends Model {
	static{
		//validateWith(new ResultValidator()).message("ingrese el tipo");
		validateNumericalityOf("cant_gol_visit", "cant_gol_local","puntos_obtenidos").greaterThan(-1).onlyInteger().message("Cantidad de puntos incorrecta");
	}
	public void setTipo(String nuevoTipo){ //cambia el tipo_result
		set("tipo_Result", nuevoTipo);
	}
	
	public void setCantGV(Integer cantV){ //cambia la goles como visitante	
		setInteger("cant_gol_visit", cantV); }
	
	public Integer getCantGV(){ //obtiene la cantidad de goles como visitante
		return getInteger("cant_gol_visit");
	}	
	
	public void setCantGL(Integer cantL){ //cambia los goles locales	
		setInteger("cant_gol_local", cantL); }

	public Integer getCantGL(){ //obtiene la cantidad de goles como local
		return getInteger("cant_gol_local");
	}	

	public void setPuntos(int puntos){ //modificar los puntos del usuario	
		setInteger("puntos_obtenidos", puntos); }
	
	public int getPuntos(){ //obtiene los puntos dado un bet y un result
		return getInteger("puntos_obtenidos");
	}	
		
	
}
