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
		
			Fixture fix1 = new Fixture();
      fix1.set("nombre", "   ");
      assertEquals(fix1.isValid(), false);
		
  }
}
