package prode;

import org.javalite.activejdbc.Model;

public class Score extends Model {

	static{
		validateNumericalityOf("cant_puntos", "partidos_acertados").greaterThan(-1).onlyInteger().message("Cantidad de puntos incorrecta");
		//validateNumericalityOf("partidos_acertados").greaterThan(0).onlyInteger().message("Cantidad de Partidos Acertados incorrecta");
	}

/*
	| id                 | int(11)      | NO   | PRI | NULL    | auto_increment |
| cant_puntos        | int(11)      | YES  |     | NULL    |                |
| partidos_acertados | int(11)      | YES  |     | NULL    |                |
| username_player    | varchar(128) | YES  | MUL | NULL    |                |
| id_bet  */
}