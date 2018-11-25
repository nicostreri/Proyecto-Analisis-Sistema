package prode;

import prode.exceptions.*;
import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;
import prode.*;
import prode.controladores.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App{
    public static void main( String[] args ){
    	Metrics.iniciar();
    	port(8081);

    	//Filtros
    	before("*", Filters.preGeneral);
		after("*", Filters.posGeneral);
		before("/protegido/*", Filters.seccionProtegida);
		before("/api/*", Filters.seccionAdmin);
		before("/admin", Filters.seccionAdmin);


		get("/", GeneralController.index, new MustacheTemplateEngine());
		get("/stop", GeneralController.stop);

		get("/partido-estadistica/:id", ApiController.estadisticaPartido);

		//Registro de Usuario
		get("/registro", UserController.registrarForm, new MustacheTemplateEngine());
		post("/registro", UserController.registroUser, new MustacheTemplateEngine());
		
		//Login de Usuario
		get("/login",UserController.loginForm, new MustacheTemplateEngine());
	    post("/login", UserController.login, new MustacheTemplateEngine());
	    
	    //Secciones Publicas
	    get("/fecha/:id", FechaController.listarPartidosDeFecha, new MustacheTemplateEngine());
		get("/fixture", FixtureController.listarTodosFixtures, new MustacheTemplateEngine());
	    get("/fixture/:id",FixtureController.listarFechasDeFixture, new MustacheTemplateEngine());
        get("/fixture/:id/ganadores",FixtureController.listarGanadoresDeFixture, new MustacheTemplateEngine());


	    //Secciones Privadas, Acceso con user logeado
	    get("/protegido/perfil", UserController.perfil, new MustacheTemplateEngine());
	    get("/salir", UserController.salir);
	    post("/protegido/fecha/:id", FechaController.apostarFecha);

        get("/protegido/misfixture",FixtureController.listarMisFixture,new MustacheTemplateEngine());
        post("/protegido/misfixture",FixtureController.suscribirPlayerFixture);
      	get("/protegido/apuestas",UserController.listarApuestas,new MustacheTemplateEngine());
      	get("/protegido/apuestas/:id",UserController.listarPartidosApostados,new MustacheTemplateEngine());  


	    //Routers Api, solo acceso Administradores
	    get("/admin", AdminController.mainAdmin, new MustacheTemplateEngine()); //Panel de Administracion
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
    	exception(ApuestaFechaException.class, ExceptionController.catchExceptions(400));
    	exception(Exception.class, (exception, request, response) -> {
    		response.body( exception.getMessage());
    		exception.printStackTrace();
		});
    }
}
