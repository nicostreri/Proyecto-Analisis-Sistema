package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class ResultValidator extends ValidatorAdapter{
	
	@Override
	public void validate(Model m){
		boolean notValid = true;
		String te = m.getString("type_result");

		if (te != null ) {
			switch(te) {
			case "empate" : notValid = false;
			break;
			case "gana_local" : notValid = false;
			break;
			case "gana_visitante" : notValid = false;
			break;
			case "no_jugado" : notValid = false;
			break;
			
			}
		};
		
		if(notValid) {
         	m.addValidator(this, "enum_error");
		}
	}	
}

