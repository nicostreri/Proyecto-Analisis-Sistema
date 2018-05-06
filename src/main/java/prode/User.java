package prode;

import org.javalite.activejdbc.Model;

public class User extends Model {

  static{
    validatePresenceOf("username").message("Please, provide your username");
    validatePresenceOf("password").message("Proveer password");
  	validateRegexpOf("username","\\b([A-Z0-9a-z])\\w+\\b").message("Formato de username incorrecto. Mayus/Minus y Numeros");

  }
}
