package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;

public class FixtureController{
	public static TemplateViewRoute listarTodosFixtures = (req, res) -> {
	    //Se obtiene los Fixtures con el id y el nombre
	    List<Map<String,String>> fixtures = new ArrayList();
	    List<Fixture> temp = Fixture.find("*");
	    for(Fixture t : temp){
	    	//Por cada Fixture
	    	fixtures.add(t.getDatos());
	    }
	    Map respuesta = new HashMap();
	    respuesta.put("hay_elem",fixtures);
	    return new ModelAndView(respuesta, "./views/listFixtures.mustache");
	};

     public static TemplateViewRoute listarMisFixture = (req, res) -> {
        List<Map<String,String>> fixSus = new ArrayList();
        List<Map<String,String>> fixNoSus = new ArrayList();
        List<Fixture> temp = Fixture.find("*");
        for(Fixture t : temp){
           if(Util.userSuscripto(req.session().attribute("username"),t.getString("id"))){
              fixSus.add(t.getDatos());
           }else{
              fixNoSus.add(t.getDatos());            
           }
        }
        Map respuesta = new HashMap();
        respuesta.put("hay_elem_fixSus",fixSus);
        respuesta.put("hay_elem_fixNoSus",fixNoSus);
        return new ModelAndView(respuesta, "./views/listFixturesSus.mustache");
     };    

	public static TemplateViewRoute listarFechasDeFixture =  (req, res) -> {
	    //Se obtiene el Fixture con el id
	    Fixture temp = Fixture.findById(req.params(":id"));
	    if(temp != null){
	    	List<Map<String,String>> fechas = new ArrayList();
	    	for(Schedule t : temp.obtenerListaSchedules()){
	    		fechas.add(t.getDatos());
	    	}
	    	Map respuesta = new HashMap();
	    	respuesta.put("hay_elem",fechas);
	    	respuesta.put("name_fixture",temp.getString("name"));
			return new ModelAndView(respuesta, "./views/listFechas.mustache");
		}
		return null;
	};
    
    public static TemplateViewRoute listarGanadoresDeFixture = (req,res) -> {
      String idFix=req.params(":id");
      Fixture tempFixture = Fixture.findById(idFix);
      List<Map<String,String>> datos = new ArrayList();
      //if(Util.fixtureCerrado(idFix)){
        List<Player> tempPlayerGanadores = new ArrayList();
        List<Player> tempPlayers = tempFixture.obtenerListaPlayer();
        int maxPunt=0;
        for(Player p : tempPlayers){
          List<Score> tempScore = p.obtenerListaScores();
          int auxPunt=0;
          for(Score s : tempScore){
            Bet tempBet = s.obtenerBet();
            Schedule tempSchedule = tempBet.obtenerSchedule();
            Fixture auxFixture = tempSchedule.obtenerFixturePerteneciente();
            if(auxFixture.getString("id").equals(idFix)){
              auxPunt+= s.getPoints();
            }
          }  
          if(auxPunt >= maxPunt){
              if(auxPunt > maxPunt){
                tempPlayerGanadores.clear();
                tempPlayerGanadores.add(p);
                maxPunt=auxPunt;
              }else{
                tempPlayerGanadores.add(p);
              }
          }
        }
        int i = 1;
        for(Player pa : tempPlayerGanadores){
            Map<String,String> tempDatos = new HashMap();
            tempDatos.put("num",Integer.toString(i));
            tempDatos.put("username",pa.getUsername());
            tempDatos.put("punt_total",Integer.toString(maxPunt));
            datos.add(tempDatos);
            i++;
        }        
    //}   
      Map respuesta = new HashMap();
      respuesta.put("fix_name",tempFixture.getName());
      respuesta.put("hay_elem",datos);
      return new ModelAndView(respuesta,"./views/listPlayersGanadoresFix.mustache");  
    };    

    public static Route suscribirPlayerFixture = (req,res)->{
        String usernamePlayer = req.session().attribute("username");
        String idFixture = req.queryParams("idFix");
        if(idFixture == null){
            return null;        
        }
        if(Util.userSuscripto(usernamePlayer,idFixture)){
            res.body("El usuario ya esta suscrito");
            return null;
        }else{
            PlayersFixtures.createIt("player_username", usernamePlayer,"fixture_id",idFixture);
        }
        res.body("Suscrito correctamente!");
        return null;
    };

}
