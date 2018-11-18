package prode.controladores;

import prode.*;
import spark.*;
import java.util.*;

public class ExceptionController {
	
	public static ExceptionHandler catchExceptions(int code){
		return (exception, request, response) -> {
			response.body(exception.getMessage());
			response.status(code);
		};
	}
}