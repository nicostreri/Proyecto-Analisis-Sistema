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
	    res.redirect("/login");
	    return null;
	};

	public static TemplateViewRoute registrar = (req, res) -> {
	    return new ModelAndView(null, "./views/registro.mustache");
	};

	public static TemplateViewRoute perfil = (req, res) -> {
		User temp = User.findById(req.session().attribute("username"));
		Map respuesta = new HashMap();
		respuesta.put("username",temp.getUsername());
		respuesta.put("nombre_apellido",temp.getNombreCompleto());
		return new ModelAndView(respuesta, "./views/perfil.mustache");
	};
}