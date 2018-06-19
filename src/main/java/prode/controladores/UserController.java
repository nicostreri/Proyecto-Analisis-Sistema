package prode.controladores;

import prode.*;
import spark.*;
import java.util.*;

public class UserController {
    public static TemplateViewRoute loginForm = (Request req, Response res) -> {
    	if(Util.userLogeado(req)){
	    	res.redirect("/protegido/perfil");
	    	return null;
	    }
	    return new ModelAndView(null, "./views/login.mustache");
    };

    public static TemplateViewRoute login = (Request req,Response res) -> {
	    //Procesamiento de Login
	    boolean loginCorrecto = false;
	    String error = "";
	    if(Util.userLogeado(req)){
	   		loginCorrecto = true;
	    }else{
			String user = req.queryParams("username");
		    String pass = req.queryParams("claveinput");
		    if(user != null && pass!= null && !user.equals("") && !pass.equals("")){
		    	if(User.logear(user,pass)!=null){
		    		req.session().attribute("logeado", true);
		    		req.session().attribute("username", user);

		    		//Se determina si es un usuario Administrador
		    		if(Administrator.findById(user) != null){
		    			req.session().attribute("isAdmin", true);
		    		}

		    		loginCorrecto = true;
		    	}else{
		    		error = "Los datos son incorrectos";
		    	}
		    }else{
		    	error = "Complete todos los datos";
		    }
		}
	    if(loginCorrecto){
	    	res.redirect("/protegido/perfil");
	    }else{
	    	res.status(401);
	    	Map<String,String> r = new HashMap();
	    	r.put("error", error);
	    	return new ModelAndView(r, "./views/login.mustache");
	    }
	    return null;
	};

	public static Route salir = (Request req,Response res) -> {
		req.session().removeAttribute("logeado");
		req.session().removeAttribute("isAdmin");
	    res.redirect("/login");
	    return null;
	};

	public static TemplateViewRoute registrar = (req, res) -> {
	    return new ModelAndView(null, "./views/registro.mustache");
	};

	public static TemplateViewRoute perfil = (req, res) -> {
    List<Map<String,String>> datos = new ArrayList();
    String userName=req.session().attribute("username");
		User tempUser = User.findById(userName);
    Player tempPlayer = Player.findById(userName);
    List<Score> tempScores = tempPlayer.obtenerListaScores();
    int i = 1;
    for(Score s : tempScores){
      Map<String,String> tempDatos = new HashMap();
      Bet tempBet = s.obtenerBet();
      Schedule tempSchedule = tempBet.obtenerSchedule();
      Fixture tempFixture = tempSchedule.obtenerFixturePerteneciente();  
      tempDatos.put("id",Integer.toString(i));
      tempDatos.put("name_fecha",tempSchedule.getFecha());
      tempDatos.put("name_fix",tempFixture.getName());
      tempDatos.put("point",s.getPoints());
      datos.add(tempDatos);      
      i++;
    }   
		Map respuesta = new HashMap();
    respuesta.put("hay_elem",datos);
		respuesta.put("username",tempUser.getUsername());
		respuesta.put("nombre_apellido",tempUser.getNombreCompleto());
		return new ModelAndView(respuesta, "./views/perfil.mustache");
	};
}
