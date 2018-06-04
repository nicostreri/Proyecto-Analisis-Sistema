package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

public class ResultValidator extends ValidatorAdapter{
	
	@Override
	public void validate(Model m){
		boolean valid = true;
		String te = m.getString("type_result");

		if(te == null || te.equals("")){
			valid = false;
		}else{
			if(!te.equals("empate") && !te.equals("gana_local") && !te.equals("gana_visitante") && !te.equals("no_jugado")) {
				valid = false;
			}
		}
		if(!valid)
         		m.addValidator(this, "enum_error");				
	}	
}

