package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class PredictionValidator extends ValidatorAdapter{
	
	@Override
	public void validate(Model m){
		boolean valid = true;
		String te = m.getString("result_type");

		if(te == null || te.equals("")){
			valid = false;
		}else{
			if(!te.equals("empate") && !te.equals("gana_local") && !te.equals("gana_visitante")) {
				valid = false;
			}
		}
		if(!valid)
         		m.addValidator(this, "enum_error");				
	}	
}

