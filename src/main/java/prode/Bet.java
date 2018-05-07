package prode;

import org.javalite.activejdbc.Model;

public class Bet extends Model {

	 static{
    	validatePresenceOf("fecha_apuesta").message("Proveer fecha_apuesta");
  		validateRegexpOf("fecha_apuesta","\\b([0-9])\\w+\\b").message("Formato de fecha incorrecto.Numeros y guion - ");//FORMATO: 'YYYY-MM-DD HH:MM:SS' Agregar guion,espacio y los 2 puntos.
  }

}
