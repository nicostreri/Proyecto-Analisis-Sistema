package prode;

import org.javalite.activejdbc.Model;
import java.util.List;

import org.javalite.activejdbc.annotations.*;
@IdName("username")
public class Administrator extends Model {
	static{
		validatePresenceOf("username").message("Ingrese el nombre del usuario");
	}
	//cambia el nombre del usuario
	public void setUsername(String nombre){ 
		setString("username", nombre);
	}
	//obtengo el nombre del usuario
	public String getUsername(){ 
		return getString("username");
	}
}