package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class ResultValidator extends ValidatorAdapter{
	
	@Override
	public void validate(Model m){
		boolean notValid = true;
		String te = m.getString("type_result");

		if (te != null && !te.equals("")) {
			if (te.equals("empate") || te.equals("gana_local") || te.equals("gana_visitante") || te.equals("no_jugado")) {
				notValid = false;
			}	
		};
		
		if(notValid) {
         	m.addValidator(this, "enum_error");
		}
	}	
}

