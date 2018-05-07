package prode;

import org.javalite.activejdbc.Model;

public class Match extends Model {

	static{
    	validatePresenceOf("fecha").message("Proveer fecha");
  		validateRegexpOf("fecha","\\b([0-9])\\w+\\b").message("Formato de fecha incorrecto.Numeros y guion - ");
  }//FORMATO: 'YYYY-MM-DD HH:MM:SS' Agregar guion,espacio y los 2 puntos.

}
