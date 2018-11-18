package prode.exceptions;

public class ApuestaFechaException extends Exception{
	public ApuestaFechaException(String mensaje){
		super("Error: " + mensaje);
	}
}