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
		return new ModelAndView(null, "./views/index.mustache");
	};
}