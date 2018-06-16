package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class Result extends Model {
	
	static{
		//validateWith(new ResultValidator()).message("ingrese el tipo"); //valida el tipo TResult
		validatePresenceOf("result_type").message("Ingrese tipo de resultado");
		validateNumericalityOf("visit_goals", "local_goals").greaterThan(-1).onlyInteger().message("Cantidad de Goles incorrecta");
	}
	public String getTipo(){
		return getString("result_type");	
	}
	
	public void setTipo(String nuevoTipo){
		set("result_type", nuevoTipo);
	}
	
	public void setCantGV(Integer cantV){ //cambia la goles como visitante	
		this.setInteger("visit_goals", cantV); 
	}
	
	public Integer getCantGV(){ //obtiene la cantidad de goles como visitante
		return getInteger("visit_goals");
	}	
	
	public void setCantGL(Integer cantL){ //cambia los goles locales	
		setInteger("local_goals", cantL); }

	public Integer getCantGL(){ //obtiene la cantidad de goles como local
		return getInteger("local_goals");
	}		
}