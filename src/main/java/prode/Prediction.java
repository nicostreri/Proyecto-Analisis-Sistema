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
	
	public void setHit(Boolean h){
		setBoolean("hit", h);
	}

    public boolean getHit(){
        return this.getBoolean("hit");
    }

    public String getTipo(){
		return getString("result_type");	
	}
}
