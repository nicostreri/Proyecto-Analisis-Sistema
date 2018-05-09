package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class Result extends Model {
	
	static{
		//validateWith(new ResultValidator()).message("ingrese el tipo"); //valida el tipo TResult
		validatePresenceOf("tipo_result").message("Ingrese tipo de resultado");
		validateNumericalityOf("cant_goles_visit", "cant_goles_local").greaterThan(-1).onlyInteger().message("Cantidad de puntos incorrecta");
	}
	public String getTipo(){
		return getString("tipo_result");	
	}
	
	public void setTipo(String nuevoTipo){
		set("tipo_result", nuevoTipo);
	}
	
	public void setCantGV(Integer cantV){ //cambia la goles como visitante	
		setInteger("cant_goles_visit", cantV); }
	
	public Integer getCantGV(){ //obtiene la cantidad de goles como visitante
		return getInteger("cant_goles_visit");
	}	
	
	public void setCantGL(Integer cantL){ //cambia los goles locales	
		setInteger("cant_goles_local", cantL); }

	public Integer getCantGL(){ //obtiene la cantidad de goles como local
		return getInteger("cant_goles_local");
	}		
}
