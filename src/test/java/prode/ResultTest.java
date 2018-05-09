package prode;

import prode.Result;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("ResultTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("ResultTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validateTipoResultado(){
      Result resultado = new Result();
      resultado.setTipo("");
      assertEquals(resultado.isValid(), false);

      Result resultado2 = new Result();
      resultado.setTipo(null);
      assertEquals(resultado2.isValid(), false);
  }
  
  @Test
  public void validacionCantGoles(){
  	/*
		Siguiendo la tabla de verdad se obtienen 4 combinaciones
		Pos y Pos
		Pos y Neg
		Neg y Pos
		Neg y Neg 
		Solo el primer caso es valido
  	*/

	Result resul1 = new Result();
	resul1.setCantGV(3);
	resul1.setCantGL(3);
	resul1.setTipo("NoJugado");
	
	
	Result resul2 = new Result();
	resul2.setCantGL(-20);
	resul2.setCantGV(-5);
	resul2.setTipo("NoJugado");
	

	Result resul3 = new Result();
	resul3.setCantGL(-20);
	resul3.setCantGV(5);
	resul3.setTipo("NoJugado");

	Result resul4 = new Result();
	resul4.setCantGL(20);
	resul4.setCantGV(-5);
	resul4.setTipo("NoJugado");
	
	
	assertEquals(resul1.isValid(), true);
	assertEquals(resul2.isValid(), false);
	assertEquals(resul3.isValid(), false);
	assertEquals(resul4.isValid(), false);
  } 

}
