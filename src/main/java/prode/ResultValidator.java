package prode;

import org.javalite.activejdbc.*;
/*
public class ResultValidator extends ValidatorAdapter{
	
	void validate(Model m){
		boolean valid = true;
		String te = m.getString("tipo_result");

		if(te == null || te == ""){
			valid = false;
		}else{
			if(te != "empate" && te != "GanaLocal" && te != "GanaVisitante" && te != "NoJugado" ){
				valid = false;
			}
		}
		if(!valid)
         		m.addValidator(this, "enum_error");				
	}	
}

*/
