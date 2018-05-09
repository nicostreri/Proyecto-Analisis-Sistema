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
  public void validateTipoReultado(){
      Result resultado = new Result();
      resultado.setTipo("");
      assertEquals(resultado.isValid(), false);

      Result resultado2 = new Result();
      resultado.setTipo(null);
      assertEquals(resultado2.isValid(), false);
  }
  
  @Test
  public void validacionCantGoles(){
	Result resul1 = new Result();
	resul1.setCantGV(3);
	
	
	Result resul2 = new Result();
	resul2.setCantGL(-20);
	

	Result resul3 = new Result();
	resul3.setCantGL(null);
	
	
	assertEquals(resul1.isValid(), true);
	assertEquals(resul2.isValid(), false);
	assertEquals(resul3.isValid(), false);
  } 

}
