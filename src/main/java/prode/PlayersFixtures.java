package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;
@Table("players_fixtures")
public class PlayersFixtures extends Model {
	
	
	/*public Player obtenerPlayer(){ //Retorna el player que se inscribò en un fixture
		Player temp = Player.findFirst("username=?", this.getUsername("username"));
		return temp;
	}

	public Fixture obtenerFixture(){ //Retorna el nombre del fixture al cual ingreso el player
		Fixture temp = Fixture.findFirst("nombre=?", this.get("nombre"));
		return temp;
	}
	*/	
}
