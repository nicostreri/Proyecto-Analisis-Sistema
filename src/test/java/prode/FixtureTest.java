package prode;

import prode.Fixture;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixtureTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("FixtureTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("FixtureTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfName(){
      Fixture fix = new Fixture();
      fix.set("nombre", "");
      assertEquals(fix.isValid(), false);
  }

	  /*
    Solo son validos nombres de fixtures formados por A-Z a-z 0-9.
    No se admiten ningun otro caracter  
  */
  @Test
  public void validacionNombreFixturesCorrectos(){
      Fixture fix1 = new Fixture();
      fix1.set("nombre", "Rusia2018#");

      Fixture fix2 = new Fixture();
      fix2.set("nombre", "Rusia2018");

     	Fixture fix3 = new Fixture();
      fix3.set("nombre", "Rusia 2018");

      Fixture fix4 = new Fixture();
      fix4.set("nombre", "2018Rusia");

      assertEquals(fix1.isValid(), false);
      assertEquals(fix2.isValid(), true);
      assertEquals(fix3.isValid(), false);
      assertEquals(fix4.isValid(), true);
  }
}
