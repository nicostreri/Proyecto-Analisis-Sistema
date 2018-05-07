package prode;
import org.javalite.activejdbc.Model;

public class Fixture extends Model{
	static{
		validatePresenceOf("nombre").message("Proveer nombre");
		validateRegexpOf("nombre","\\b([A-Z0-9a-z])\\w+\\b").message("Formato de nombre fixture incorrecto. Mayus/Minus y Numeros");
	}
}
