package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;

public class GeneralController {
	public static Route stop = (Request req, Response res) -> {
	    new Thread(() -> stop()).start();
	    Base.close();
	    return "Sistema Apagado! :(";
    };

    public static TemplateViewRoute index = (Request req, Response res) -> {
		List<Map<String,String>> datos = new ArrayList<Map<String,String>>();
		List<Player> allPlayers = Player.findAll();
		List<Player> topPlayers = new ArrayList<Player>();
		int auxPunt = 0;
		for (Player p : allPlayers) {
			/*if (Administrator.findById(p.getUsername()) != null) {
				continue;
			}*/
			auxPunt = p.getPoint();
			if (auxPunt == 0) {
				continue;
			}
			if (topPlayers.size() == 0) {
				topPlayers.add(p);
			} else {
				for (int i = 0; i < topPlayers.size() && i < 10; i++) {
					if (auxPunt >= topPlayers.get(i).getPoint() ) {
						topPlayers.add(i,p);
						break;
					}
					if (i == topPlayers.size()-1) {
						topPlayers.add(p);
						break;
					}
				}
			}
		}
		Map<String, Object> respuesta = new HashMap<String, Object>();
		if (topPlayers.size() == 0) {
			respuesta.put("titulo","Informacion no disponible");
		} else {
			for (int k = 0; k < topPlayers.size() && k < 10; k++) {
				HashMap<String,String> tempData = new HashMap<String, String>();
				tempData.put("num",Integer.toString(k+1));
				tempData.put("username",topPlayers.get(k).getUsername());
				tempData.put("punt",Integer.toString(topPlayers.get(k).getPoint()));
				datos.add(tempData);
			}
			respuesta.put("hay_elem", datos);
			respuesta.put("titulo","Top 10 mejores jugadores");
			respuesta.put("mostrar",true);
		}
		return new ModelAndView(respuesta, "./views/index.mustache");
	};
}