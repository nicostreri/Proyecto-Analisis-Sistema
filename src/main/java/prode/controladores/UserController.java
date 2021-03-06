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
            Metrics.requestsLogin.mark();
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

	public static TemplateViewRoute registrarForm = (req, res) -> {
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
      if(Integer.valueOf(s.getPoints()) >= 0 ){
          Map<String,String> tempDatos = new HashMap();
          Bet tempBet = s.obtenerBet();
          Schedule tempSchedule = tempBet.obtenerSchedule();
          Fixture tempFixture = tempSchedule.obtenerFixturePerteneciente();  
          tempDatos.put("num",Integer.toString(i));
          tempDatos.put("name_fecha",tempSchedule.getFecha());
          tempDatos.put("name_fix",tempFixture.getName());
          tempDatos.put("point",s.getPoints().toString());
          tempDatos.put("id_bet",tempBet.getString("id"));
          datos.add(tempDatos);
          i++;
      }
    }   
		Map respuesta = new HashMap();
    respuesta.put("hay_elem",datos);
		respuesta.put("username",tempUser.getUsername());
		respuesta.put("nombre_apellido",tempUser.getNombreCompleto());
    respuesta.put("punt_total",Integer.toString(tempPlayer.getPoint()));
		return new ModelAndView(respuesta, "./views/perfil.mustache");
	};

    public static TemplateViewRoute listarPartidosApostados = (req,res) ->{
    	String userName=req.session().attribute("username");
     	//User tempUser = User.findById(userName);
      	Player tempPlayer = Player.findById(userName);   
      	Score tempScore = Score.findById(req.params(":id"));

		Bet tempBet = Bet.findById(req.params(":id"));

	    if(tempBet.obtenerPlayer().getUsername().equals(tempPlayer.getUsername())){
	    	//El usuario Logeado es el Dueño de la Apuesta
	      	String idBet = tempBet.getString("id");
			Schedule tempSchedule = tempBet.obtenerSchedule();
	      	List<Map<String,String>> datos = new ArrayList(); 

	      	if(tempSchedule != null){
	        	List<Match> tempMatch = tempSchedule.obtenerListaPartidos();
	          	for(Match m : tempMatch){
	            	Result tempResult = m.obtenerResultado();
	              	BetsResults tempBetResult = BetsResults.findByCompositeKeys(idBet,tempResult.getString("id"));
		            if(tempBetResult != null ){
		                Prediction tempPrediction = Prediction.findById(tempBetResult.getIdPrediction());
		                Map<String,String> tempDatos = m.getDatos();
		               	tempDatos.put("apuesta",tempPrediction.TipoToString());
			            tempDatos.put("result", (tempSchedule.getBoolean("calculated")) ? ((tempPrediction.getHit())? "Acerto" : "No Acerto") : "No Calculado");
			            datos.add(tempDatos);
		            }
	           	}       
	      	}
        	Map respuesta = new HashMap();
        	respuesta.put("fecha_name",tempSchedule.getString("date_name"));
        	respuesta.put("hay_elem",datos);
        	return new ModelAndView(respuesta,"./views/listPartidosApostados.mustache");
      	}else{
        	res.status(401);
        	return null;
      	}
    };
  
   	public static TemplateViewRoute listarApuestas = (req,res) ->{
    	List<Map<String,String>> datos = new ArrayList();
    	String userName=req.session().attribute("username");
		User tempUser = User.findById(userName);
    	Player tempPlayer = Player.findById(userName);
    	List<Bet> tempBets = tempPlayer.obtenerListaBets();
    	int i = 1;
    	for(Bet b : tempBets){
        	Map<String,String> tempDatos = new HashMap();
        	Schedule tempSchedule = b.obtenerSchedule();
        	Fixture tempFixture = tempSchedule.obtenerFixturePerteneciente();  
        	tempDatos.put("num",Integer.toString(i));
        	tempDatos.put("name_fecha",tempSchedule.getFecha());
        	tempDatos.put("name_fix",tempFixture.getName());
        	tempDatos.put("id_bet",b.getString("id"));
        	datos.add(tempDatos);
         	i++;  
    	}
    	Map respuesta = new HashMap();
    	respuesta.put("hay_elem",datos);
    	return new ModelAndView(respuesta,"./views/listApuestas.mustache");  
   	};

   	private static String _parametroNoNull(String a){
   		return (a == null) ? "" : a;
   	}

    public static TemplateViewRoute registroUser = (Request req,Response res) -> {
        boolean registroCorrecto = false;
        String error = "";

        //Se capturan los datos del Formulario
        String username = UserController._parametroNoNull(req.queryParams("usuario"));
        String nombre = UserController._parametroNoNull(req.queryParams("nombre"));
        String apellido = UserController._parametroNoNull(req.queryParams("apellido"));
        String contrasena = UserController._parametroNoNull(req.queryParams("pwd1"));
        String contrasenaConfir = UserController._parametroNoNull(req.queryParams("pwd2"));

        if(username.equals("") || contrasena.equals("") || nombre.equals("") || apellido.equals("") || contrasenaConfir.equals("")){
            error = "Error: Complete todos los Datos.";
            res.status(400);
        }else{
            //Se completo todo el formulario
            if(contrasena.equals(contrasenaConfir)){
                //La comprobacion de Clave es correcta
                if(User.findById(username) == null){
                    //El usuario no existe, se creara en la Base de Datos como User y como Player
                    User nuevo = new User();
                    nuevo.setString("username", username);
                    nuevo.setString("name", nombre);
                    nuevo.setString("lastname", apellido);
                    nuevo.setString("password", contrasena);
                    nuevo.insert();

                    Player pNuevo = new Player();
                    pNuevo.setString("username",nuevo.getId());
                    pNuevo.insert();
                    registroCorrecto = true;
                    res.redirect("/login");
                    return null;
                }else{
                    error="Error: El username elegido esta en uso.";
                }
            }else{
                error="Error: Las Contrasenas no coinciden.";
            }
        }
        
        Map<String, String> info = new HashMap();
        if(!registroCorrecto){
            info.put("error", error);
        }
        return new ModelAndView(info, "./views/registro.mustache");
    };
}
