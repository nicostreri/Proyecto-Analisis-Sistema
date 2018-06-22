package prode;

import prode.*;
import prode.controladores.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App{
    public static void main( String[] args ){
    	port(8081);

    	//Routers
    	before("*", Filters.preGeneral);
		after("*", Filters.posGeneral);
		before("/protegido/*", Filters.seccionProtegida);
		before("/api/*", Filters.seccionAdmin);
		before("/admin", Filters.seccionAdmin);
		get("/", GeneralController.index, new MustacheTemplateEngine());
		get("/registro", UserController.registrarForm, new MustacheTemplateEngine());
		post("/registro", UserController.registroUser, new MustacheTemplateEngine());
		get("/login",UserController.loginForm, new MustacheTemplateEngine());
	    post("/login", UserController.login, new MustacheTemplateEngine());
	    get("/protegido/perfil", UserController.perfil, new MustacheTemplateEngine());
	    get("/salir", UserController.salir);
	    get("/stop", GeneralController.stop);
	    get("/fecha/:id", FechaController.listarPartidosDeFecha, new MustacheTemplateEngine());
	    post("/protegido/fecha/:id", FechaController.apostarFecha);
		get("/fixture", FixtureController.listarTodosFixtures, new MustacheTemplateEngine());
	    get("/fixture/:id",FixtureController.listarFechasDeFixture, new MustacheTemplateEngine());
        get("/fixture/:id/ganadores",FixtureController.listarGanadoresDeFixture, new MustacheTemplateEngine());
	    get("/admin", AdminController.mainAdmin, new MustacheTemplateEngine());
        get("/protegido/fechaApostada/:id",UserController.listarPartidosApostados, new MustacheTemplateEngine());
        get("/protegido/misfixture",FixtureController.listarMisFixture,new MustacheTemplateEngine());
        post("/protegido/misfixture",FixtureController.suscribirPlayerFixture);
      get("/protegido/apuestas",UserController.listarApuestasSinCalcular,new MustacheTemplateEngine());
      get("/protegido/apuestas/:id_bet",UserController.listarPredicciones,new MustacheTemplateEngine());  

	    //Routers Api
	    get("/api/equipo", ApiController.listarEquipos);
	    get("/api/fixture", ApiController.listarFixture);
	    get("/api/fixture/:idFix", ApiController.listarFecha);
	    post("/api/fixture", ApiController.nuevoFixture);
	    get("/api/fecha/:idFecha", ApiController.listarPartido);
	    post("/api/partido/:idPartido/resultado", ApiController.cargarResultado);
	    post("/api/fixture/:idFix/fecha", ApiController.nuevaFecha);
	    post("/api/equipo", ApiController.nuevoEquipo);
	    post("/api/partido", ApiController.nuevoPartido);
	    post("/api/fecha/:idFecha/calcular", FechaController.calcularFecha);

    	//Control de  Exceptions
    	exception(Exception.class, (exception, request, response) -> {
    		response.body( exception.getMessage());
		});
    }
}
