package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;
@IdName("username")
public class User extends Model {
    
    static{
        validatePresenceOf("username").message("Ingrese username");
        validatePresenceOf("password").message("Ingrese clave");

        //Solo se permitn usernames que esten formados por letras maysuculas y minusculas y numeros, no pueden tener caracters especiales ni espacios
        validateRegexpOf("username","\\b([A-Z0-9a-z])\\w+\\b").message("Formato de username incorrecto. Mayus/Minus y Numeros");
    }

  /*
    Retorna el Nombre y Apellido del User de la Instancia Actual, que se encuentra almacenado en base de datos o por almacenar
  */
    public String getNombreCompleto(){
        return get("name") + " " + get("lastname");
    }
        
    public String getUsername(){
        return this.getString("username");
    }

    public String getNombre(){
        return this.getString("name");
    }     

    public String getApellido(){
        return this.getString("lastname");
    }

    public static User logear(String user, String pass){
        return User.findFirst("username = ? and password = ?", user, pass);
    }
}