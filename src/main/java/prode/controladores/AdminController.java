package prode.controladores;

import prode.*;
import spark.*;
import static spark.Spark.*;
import org.javalite.activejdbc.Base;
import java.util.*;
import org.json.*;

public class AdminController{
	public static TemplateViewRoute mainAdmin = (req, res) -> {
		return new ModelAndView(null, "./views/admin.mustache");
	};
}